package com.atguigu.gulimall.product.exception;

import com.atguigu.common.constant.ExtConstant;
import com.atguigu.common.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Caler_赵康乐
 * @create 2020-11-17 15:44
 * @description :
 */
@RestControllerAdvice
public class GlobHandleException {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValid(MethodArgumentNotValidException e ){
        Map<Object, Object> map = new LinkedHashMap<>();
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(item->{
            map.put(item.getField(),item.getDefaultMessage());
        });
        return R.error(ExtConstant.VALID_EXCEPTION.getCode(),ExtConstant.VALID_EXCEPTION.getMessage()).put("data",map);
    }

    @ExceptionHandler(value = Exception.class)
    public R handleValid(Exception e ){

        return R.error(ExtConstant.UNKNOWN_EXCEPTION.getCode(),ExtConstant.UNKNOWN_EXCEPTION.getMessage());
    }
}
