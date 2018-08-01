package com.sunda.sell.controller;

import com.sunda.sell.convert.OrderForm2OrderDtoConvert;
import com.sunda.sell.dto.OrderDto;
import com.sunda.sell.enums.ResultEnum;
import com.sunda.sell.exception.SellException;
import com.sunda.sell.form.OrderForm;
import com.sunda.sell.service.OrderService;
import com.sunda.sell.utils.ResultVoUtil;
import com.sunda.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
