package com.sunda.sell.enums;

import lombok.Getter;

/**
 * Created by 老蹄子 on 2018/7/31 下午7:20
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0,"上架"),
    DOWN(1,"下架")
    ;


    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
