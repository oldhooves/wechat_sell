package com.sunda.sell.service.impl;

import com.sunda.sell.convert.OrderMaster2OrderMasterDto;
import com.sunda.sell.dataObject.OrderDetail;
import com.sunda.sell.dataObject.OrderMaster;
import com.sunda.sell.dataObject.ProductInfo;
import com.sunda.sell.dto.CartDto;
import com.sunda.sell.dto.OrderDto;
import com.sunda.sell.enums.OrderStatusEnum;
import com.sunda.sell.enums.PayStatusEnum;
import com.sunda.sell.enums.ResultEnum;
import com.sunda.sell.exception.SellException;
import com.sunda.sell.repository.OrderDetailRepository;
import com.sunda.sell.repository.OrderMasterRepository;
import com.sunda.sell.service.OrderService;
import com.sunda.sell.service.ProductService;
import com.sunda.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 老蹄子 on 2018/8/1 下午1:23
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    BigDecimal orderAmount = new BigDecimal(0);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        String orderId = KeyUtil.getUniqueKey();
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);

            orderDetailRepository.save(orderDetail);
        }

        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().
                map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDto> orderDtoList = OrderMaster2OrderMasterDto.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {

        OrderMaster orderMaster = new OrderMaster();


        //判断订单状态
        if (!(orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))){
            log.error("[取消订单，订单状态不正确],orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATU_ERROR);
        }

        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【取消订单更新失败】,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【取消订单失败】订单中无商品详情，orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDtoList);

        //如果已经支付，需要退款
        if (orderDto.getOrderStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
            System.out.println("退款");
        }


        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {

        //判断订单状态
        if (!(orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))){
            log.error("[完结订单，订单状态不正确],orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATU_ERROR);
        }

        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【完结订单更新失败】,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {

        //判断订单状态
        if (!(orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))){
            log.error("[订单支付完成] 订单状态不正确,orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATU_ERROR);
        }

        //判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】订单支付状态不正确,orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【订单支付完成】,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderMasterDto.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());

    }
}
