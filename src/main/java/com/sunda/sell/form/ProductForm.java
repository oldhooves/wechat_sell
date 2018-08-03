package com.sunda.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 老蹄子 on 2018/8/3 下午8:28
 */
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;
}
