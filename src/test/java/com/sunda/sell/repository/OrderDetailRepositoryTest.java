package com.sunda.sell.repository;

import com.sunda.sell.dataObject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 老蹄子 on 2018/8/1 上午11:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("6666662");
        orderDetail.setProductId("3333333");
        orderDetail.setDetailId("222222");
        orderDetail.setProductIcon("http:xxx.jpg");
        orderDetail.setProductPrice(new BigDecimal(5.5));
        orderDetail.setProductName("肉夹馍");
        orderDetail.setProductQuantity(10);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception {

        List<OrderDetail> orderDetailList = repository.findByOrderId("6666662");
        Assert.assertNotEquals(0,orderDetailList.size());
    }

}