package com.atguigu.gulimall.thirdparty.fileservice.controller;


import com.atguigu.common.utils.R;
import com.atguigu.gulimall.thirdparty.fileservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Caler_赵康乐
 * @create 2020-11-21 17:06
 * @description :文件上传下载服务
 */
@RestController
@RequestMapping("/file")
public class FileServiceController {


    @Autowired
    FileService fileService;

    @GetMapping("/download")
    public R download(String path, HttpServletRequest request, HttpServletResponse response) {
        fileService.download(path,request,response);
        return R.ok().put("data", "success");
    }

    @GetMapping("/upload")
    public R upload() {
        Map<String, String> upload = fileService.upload();
        return R.ok().put("data",upload);
    }

}
