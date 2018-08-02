package com.sunda.sell.controller;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import com.sunda.sell.convert.OrderForm2OrderDtoConvert;
import com.sunda.sell.dto.OrderDto;
import com.sunda.sell.enums.ResultEnum;
import com.sunda.sell.exception.SellException;
import com.sunda.sell.form.OrderForm;
import com.sunda.sell.service.BuyerService;
import com.sunda.sell.service.OrderService;
import com.sunda.sell.utils.ResultVoUtil;
import com.sunda.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 老蹄子 on 2018/8/1 下午8:30
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/create")
    public ResultVo<Map<String,String >> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDto orderDto = OrderForm2OrderDtoConvert.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDto createResult = orderService.create(orderDto);
        Map<String,String > map = new HashMap<>();

        map.put("orderId",createResult.getOrderId());

        return ResultVoUtil.success(map);
    }

    @GetMapping("/list")
    public ResultVo<OrderDto> list(@RequestParam("openid") String openid,
                                   @RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @RequestParam(value = "size",defaultValue = "5") Integer size){

        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page,size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid,request);
        return ResultVoUtil.success(orderDtoPage.getContent());
    }

    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        OrderDto orderDto = buyerService.findOrderOne(openid,orderId);
        return ResultVoUtil.success(orderDto);
    }

    @PostMapping("/cancel")
    public ResultVo<OrderDto> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){

        OrderDto orderDto = buyerService.cancelOrder(openid,orderId);
        return ResultVoUtil.success();
    }


}
