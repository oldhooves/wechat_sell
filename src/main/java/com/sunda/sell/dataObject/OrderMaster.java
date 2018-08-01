package com.sunda.sell.dataObject;

import com.sunda.sell.enums.OrderStatusEnum;
import com.sunda.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 老蹄子 on 2018/8/1 上午10:31
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus= OrderStatusEnum.NEW.getCode() ;

    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;


}
