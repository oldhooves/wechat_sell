package com.sunda.sell.dto;

import lombok.Data;

/**
 * Created by 老蹄子 on 2018/8/1 下午2:51
 */
@Data
public class CartDto {

    private String productId;

    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
