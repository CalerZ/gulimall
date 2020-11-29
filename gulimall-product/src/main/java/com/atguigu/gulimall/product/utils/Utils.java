package com.atguigu.gulimall.product.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.util.Md5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;

import javax.jnlp.UnavailableServiceException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Caler_赵康乐
 * @create 2020-11-28 18:09
 * @description :
 */
@Component
public class Utils {
    @Autowired
    private  FormHashMap formHashMap;

    public static void PO2VO(Object entity,Object entityVo){
        try {
            BeanUtils.copyProperties(entity,entityVo);
        } catch (BeansException e) {
            e.printStackTrace();
        } finally {
        }
    }



    // 二进制读取
    public static byte[] readAsBytes(HttpServletRequest request)
    {

        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        ServletInputStream in = null;

        try
        {
            in = request.getInputStream();
            in.read(buffer, 0, len);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != in)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }


}
