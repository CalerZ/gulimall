package com.atguigu.gulimall.product.exception;

import org.hibernate.validator.constraints.CodePointLength;

/**
 * @author Caler_赵康乐
 * @create 2020-11-25 14:19
 * @description :
 */
public class ErrorStatusCodeException extends BizProductException {
    private Integer code;

    public ErrorStatusCodeException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
