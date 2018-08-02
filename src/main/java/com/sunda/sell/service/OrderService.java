package com.sunda.sell.service;

import com.sunda.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 老蹄子 on 2018/8/1 下午1:10
 */
public interface OrderService {

    OrderDto create(OrderDto orderDto);

    OrderDto findOne(String orderId);

    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    OrderDto cancel(OrderDto orderDto);

    OrderDto finish(OrderDto orderDto);

    OrderDto paid(OrderDto orderDto);

    Page<OrderDto> findList(Pageable pageable);

}
