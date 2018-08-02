package com.sunda.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sunda.sell.dataObject.OrderDetail;
import com.sunda.sell.enums.OrderStatusEnum;
import com.sunda.sell.enums.PayStatusEnum;
import com.sunda.sell.utils.EnumUtil;
import com.sunda.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.springframework.core.annotation.OrderUtils;

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

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(orderStatus,PayStatusEnum.class);
    }

}
