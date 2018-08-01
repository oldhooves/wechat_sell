package com.sunda.sell.dto;

import com.sunda.sell.dataObject.OrderDetail;
import com.sunda.sell.enums.OrderStatusEnum;
import com.sunda.sell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 老蹄子 on 2018/8/1 下午1:15
 */
@Data
public class OrderDto {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
