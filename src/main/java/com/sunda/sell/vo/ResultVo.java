package com.sunda.sell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 老蹄子 on 2018/7/31 下午7:54
 */
@Data
public class ResultVo<T> implements Serializable{

    private static final long serialVersionUID = -4558534903219443882L;

    private Integer code;

    private String msg;

    private T data;
}
