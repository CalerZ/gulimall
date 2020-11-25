package com.atguigu.gulimall.product.exception;

/**
 * @author Caler_赵康乐
 * @create 2020-11-25 14:19
 * @description :
 */
public abstract class BizProductException extends RuntimeException {
    public BizProductException(String message){
        super(message);
    }
    public BizProductException(String message,Throwable cause){
        super(message,cause);
    }
}
