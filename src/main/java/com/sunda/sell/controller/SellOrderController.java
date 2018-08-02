package com.sunda.sell.controller;

import com.sunda.sell.dto.OrderDto;
import com.sunda.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by 老蹄子 on 2018/8/2 下午8:48
 */
@Controller
@RequestMapping("/seller/order")
public class SellOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page - 1,size);
        Page<OrderDto> orderDtoPage = orderService.findList(request);
        map.put("orderDtoPage",orderDtoPage);
        return new ModelAndView("order/list",map);
    }
}
