package com.atguigu.gulimall.product.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.List;

/**
 * @author Caler_赵康乐
 * @create 2020-11-28 18:09
 * @description :
 */
public class Utils {

    public static void PO2VO(Object entity,Object entityVo){
        try {
            BeanUtils.copyProperties(entity,entityVo);
        } catch (BeansException e) {
            e.printStackTrace();
        } finally {
        }
    }


}
