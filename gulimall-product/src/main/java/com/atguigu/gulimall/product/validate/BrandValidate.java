package com.atguigu.gulimall.product.validate;

import com.atguigu.gulimall.product.exception.ErrorStatusCodeException;
import com.atguigu.gulimall.product.utils.FormHashMap;
import com.atguigu.gulimall.product.utils.Utils;
import com.atguigu.gulimall.product.vo.BrandEntityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author Caler_赵康乐
 * @create 2020-11-25 14:29
 * @description :
 */
@Component
public class BrandValidate {

    @Autowired
    FormHashMap formHashMap;


    public void validateFormDulipce(HttpServletRequest request, BrandEntityVo brand, HttpServletResponse response) {
        formHashMap.hashForm(1L,request.getRequestURI(),brand);

    }

    public void validateStatus(Integer status) {
        if(status!=0&&status!=1){
            throw new ErrorStatusCodeException(10002,"状态码验证错误！");
        }
    }
}
