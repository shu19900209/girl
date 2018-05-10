package com.sasa.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),
    PRAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详细不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPADTE_FAIL(15, "订单更新失败"),
    ORDER_PAY_STATUS_ERROR(16, "支付状态不正确"),
    CARD_EMPTY(17,"购物车为空"),
    ORDER_OWNER_ERROR(18, "订单不属于当前用户"),
    WECHAT_MP_ERROR(19,"微信公众号方面错误"),
    WECHAT_PAY_MONEY_VERTFY_ERROR(19,"微信支付异步通知金额校验不通过"),
    ORDER_CANCEL_SUCCESS(20,"订单取消成功"),
    ORDER_FINISH_SUCCESS(21,"订单完结成功"),
    PRODUCT_STATUS_ERROR(22,"商品状态不正确"),
    LOGIN_FAIL(23,"登录失败, 登录信息不正确"),
    LOGOUT_SUCCESS(24, "登出成功"),

    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
