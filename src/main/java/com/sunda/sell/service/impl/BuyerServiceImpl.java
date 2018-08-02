package com.sunda.sell.service.impl;

import com.sunda.sell.dto.OrderDto;
import com.sunda.sell.enums.ResultEnum;
import com.sunda.sell.exception.SellException;
import com.sunda.sell.service.BuyerService;
import com.sunda.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 老蹄子 on 2018/8/2 上午9:43
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid,orderId);
        if (orderDto == null){
            log.error("【取消订单】查不到该订单,orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderOwner(String openid, String orderId){
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto == null){
            return null;
        }
        if (!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致,openid={},orderDto={}",openid,orderDto);
            throw new SellException(ResultEnum.ORDER_OWNER_FAIL);
        }
        return orderDto;
    }
}
