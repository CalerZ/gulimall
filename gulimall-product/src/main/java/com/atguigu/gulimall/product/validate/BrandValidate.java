package com.atguigu.gulimall.product.validate;

import com.atguigu.gulimall.product.exception.ErrorStatusCodeException;
import org.springframework.stereotype.Component;

/**
 * @author Caler_赵康乐
 * @create 2020-11-25 14:29
 * @description :
 */
@Component
public class BrandValidate {

    public void validateStatus(Integer status) {
        if(status!=0&&status!=1){
            throw new ErrorStatusCodeException(10002,"状态码验证错误！");
        }
    }
}
