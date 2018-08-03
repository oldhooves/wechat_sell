package com.sunda.sell.enums;

import lombok.Getter;

/**
 * Created by 老蹄子 on 2018/8/1 上午10:42
 */
@Getter
public enum  PayStatusEnum implements CodeEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
