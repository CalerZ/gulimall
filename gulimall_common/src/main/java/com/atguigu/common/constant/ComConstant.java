package com.atguigu.common.constant;

/**
 * @author Caler_赵康乐
 * @create 2020-11-17 15:50
 * @description :
 *
 * 10(系统整体和gulimall_common)
 *   000
 * 11(renren-fast)
 *
 * 12(gulimall_gateway)
 * 13(gulimall-product)
 * 14(gulimall-order)
 * 15(gulimall-member)
 * 16(gulimall-coupon)
 * 17(gulimall-ware)
 * 18(gulimall-third-party)
 *
 *
 */
public enum ComConstant {
    UNKNOWN_EXCEPTION(10000,"未知异常"),
    VALID_EXCEPTION(10001,"验证错误");

    private int code;
    private String message;

    ComConstant(int code, String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
