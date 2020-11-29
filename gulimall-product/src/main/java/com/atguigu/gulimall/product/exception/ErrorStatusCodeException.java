package com.atguigu.gulimall.product.exception;

/**
 * @author Caler_赵康乐
 * @create 2020-11-25 14:19
 * @description :
 */
public class ErrorStatusCodeException extends BizCustException {

    public ErrorStatusCodeException(Integer code,String message){
        super(code,message);
    }
}
