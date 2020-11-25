package com.atguigu.gulimall.thirdparty.fileservice.service;

import com.atguigu.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Caler_赵康乐
 * @create 2020-11-21 17:25
 * @description :
 */
public interface FileService {
    /**
     * 下载文件
     */
    public void download(String path, HttpServletRequest request, HttpServletResponse response);

    /**
     * 上传文件
     * @return
     */
    public Map<String, String> upload();
}
