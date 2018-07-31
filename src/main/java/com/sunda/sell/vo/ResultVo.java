package com.sunda.sell.vo;

import lombok.Data;

/**
 * Created by 老蹄子 on 2018/7/31 下午7:54
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;
}
