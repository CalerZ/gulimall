package com.atguigu.gulimall.product.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Caler_赵康乐
 * @create 2020-11-29 20:08
 * @description :
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RepeatFormCommitValidated {

}
