package com.sunda.sell.enums;

import lombok.Getter;

/**
 * Created by 老蹄子 on 2018/8/1 下午1:33
 */
@Getter
public enum  ResultEnum {

    PARAM_ERROR(1,"参数错误"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATU_ERROR(14,"订单状态错误"),
    ORDER_UPDATE_FAIL(15,"取消订单失败"),
    ORDER_DETAIL_EMPTY(16,"订单中无商品"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态错误"),
    CART_EMPTY(18,"购物车为空")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
