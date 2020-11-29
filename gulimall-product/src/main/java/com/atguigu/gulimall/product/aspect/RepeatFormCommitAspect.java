package com.atguigu.gulimall.product.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author Caler_赵康乐
 * @create 2020-11-29 20:11
 * @description :
 */
@Aspect
@Component
@SuppressWarnings({"unused"})
public class RepeatFormCommitAspect {
    @Pointcut("@annotation(com.atguigu.gulimall.product.annotation.RepeatFormCommitValidated)")
    public void annotationPointcut() {

    }
    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        // 此处进入到方法前  可以实现一些业务逻辑
        MethodSignature methodSignature =(MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();


    }

}
