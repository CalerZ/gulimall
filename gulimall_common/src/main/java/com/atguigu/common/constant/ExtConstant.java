package com.atguigu.common.constant;

/**
 * @author Caler_赵康乐
 * @create 2020-11-17 15:50
 * @description :
 */
public enum  ExtConstant {
    UNKNOWN_EXCEPTION(10000,"未知异常"),
    VALID_EXCEPTION(10001,"验证错误");


    private int code;
    private String message;

    ExtConstant(int code,String message){
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
