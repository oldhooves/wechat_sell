package com.sunda.sell.convert;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sunda.sell.dataObject.OrderDetail;
import com.sunda.sell.dto.OrderDto;
import com.sunda.sell.enums.ResultEnum;
import com.sunda.sell.exception.SellException;
import com.sunda.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 老蹄子 on 2018/8/1 下午8:49
 */
@Slf4j
public class OrderForm2OrderDtoConvert {

    public static OrderDto convert(OrderForm orderForm){

        Gson gson = new Gson();

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误,string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }
}
