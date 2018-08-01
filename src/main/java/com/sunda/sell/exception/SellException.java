package com.sunda.sell.exception;

import com.sunda.sell.enums.ResultEnum;

/**
 * Created by 老蹄子 on 2018/8/1 下午1:32
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
