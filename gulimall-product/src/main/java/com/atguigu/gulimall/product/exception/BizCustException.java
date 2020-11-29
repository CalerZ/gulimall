package com.atguigu.gulimall.product.exception;

/**
 * @author Caler_赵康乐
 * @create 2020-11-25 14:19
 * @description :
 */
public class BizCustException extends RuntimeException {
    private Integer code;
    public BizCustException(Integer code,String message){
        super(message);
        this.code=code;
    }
    public BizCustException(String message, Throwable cause){
        super(message,cause);
    }

    public Integer getCode() {
        return code;
    }


}
