package com.sunda.sell.service.impl;

import com.sunda.sell.dataObject.OrderDetail;
import com.sunda.sell.dataObject.OrderMaster;
import com.sunda.sell.dto.OrderDto;
import com.sunda.sell.enums.OrderStatusEnum;
import com.sunda.sell.enums.PayStatusEnum;
import com.sunda.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.tools.jstat.Literal;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 老蹄子 on 2018/8/1 下午3:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {


    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPPENID = "123456789";

    private final String ORDER_ID = "1533108646936235390";

    @Test
    public void create() throws Exception {

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("老蹄子223");
        orderDto.setBuyerAddress("1231313");
        orderDto.setBuyerPhone("14987941232");
        orderDto.setBuyerOpenid(BUYER_OPPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);
        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.create(orderDto);
        log.info("[创建订单]result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDto result = orderService.findOne(ORDER_ID);
        log.info("查询单个订单result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());

    }

    @Test
    public void findList() throws Exception {

        PageRequest request = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage = orderService.findList(BUYER_OPPENID,request);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());

    }

    @Test
    public void list(){
        PageRequest request = new PageRequest(0,5);
        Page<OrderDto> orderDtoPage = orderService.findList(request);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

}