package com.sunda.sell.convert;

import com.sunda.sell.dataObject.OrderMaster;
import com.sunda.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 老蹄子 on 2018/8/1 下午4:35
 */
public class OrderMaster2OrderMasterDto {

    public static OrderDto convert(OrderMaster orderMaster){

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().
                map(e -> convert(e)).
                collect(Collectors.toList());
    }
}
