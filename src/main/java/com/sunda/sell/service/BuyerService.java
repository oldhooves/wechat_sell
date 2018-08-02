package com.sunda.sell.service;

import com.sunda.sell.dto.OrderDto;

/**
 * Created by 老蹄子 on 2018/8/2 上午9:41
 */
public interface BuyerService {

    OrderDto findOrderOne(String openid,String orderId);

    OrderDto cancelOrder(String openid,String orderId);
}
