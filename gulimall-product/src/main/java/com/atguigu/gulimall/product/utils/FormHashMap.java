package com.atguigu.gulimall.product.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.util.Md5Utils;
import com.atguigu.gulimall.product.exception.BizCustException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Caler_赵康乐
 * @create 2020-11-29 18:19
 * @description :
 */
@Component
public class FormHashMap {

    private final static Map<String,Long> CACHE = new ConcurrentHashMap();



    public static Long  get(String key){
      return CACHE.get(key);
    }

    public   boolean hashForm(Long userId, String url,Object body){
        String bodyStr = JSON.toJSONString(body);
        String key = Md5Utils.getMD5(userId + url + bodyStr, "UTF-8");

        Long time = CACHE.get(key);
        if(time==null){
            CACHE.put(key, System.currentTimeMillis()+1000*60*10);
            return true;
        }else{
            if (System.currentTimeMillis()-time>0) {
                //表单过期了
                throw new BizCustException(10002,"表单过期");
            }
            if(System.currentTimeMillis()-time<0){
                throw new BizCustException(10003,"表单重复提交");
            }
            return false;
        }
    }

}
