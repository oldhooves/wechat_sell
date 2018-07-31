package com.sunda.sell.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by 老蹄子 on 2018/7/31 下午6:19
 */
@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;


}
