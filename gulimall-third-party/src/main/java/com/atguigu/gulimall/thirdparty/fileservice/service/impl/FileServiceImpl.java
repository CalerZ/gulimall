package com.atguigu.gulimall.thirdparty.fileservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.*;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.thirdparty.fileservice.entity.OssConfigEntity;
import com.atguigu.gulimall.thirdparty.fileservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Caler_赵康乐
 * @create 2020-11-21 17:26
 * @description :
 */
@Service
@EnableConfigurationProperties(OssConfigEntity.class)
public class FileServiceImpl implements FileService {

    @Autowired
    private OssConfigEntity ossConfigEntity;

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.callbackUrl}")
    private String callbackUrl;
    @Value("${oss.dir}")
    private String dir;


    @Override
    public void download(String path, HttpServletRequest request, HttpServletResponse response) {

        BufferedInputStream input = null;
        OutputStream outputStream = null;
        try {
            String filePath = path;
            filePath = filePath.replaceAll("\\\\", "/");
            String fileName = getName(filePath);
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-msdownload");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            // endpoint,accessKeyId,accessKeySecret,bucketName是oss自带参数，登陆oss查看，或者给公司要，fileName是文件上传到oss后的文件名
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            OSSObject object = ossClient.getObject(bucketName, fileName);
            input = new BufferedInputStream(object.getObjectContent());
            byte[] buffBytes = new byte[1024];
            outputStream = response.getOutputStream();
            int read = 0;
            while ((read = input.read(buffBytes)) != -1) {
                outputStream.write(buffBytes, 0, read);
            }
            outputStream.flush();
            ossClient.shutdown();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // 获取添加后的文件名称
    public String getName(String filepath) {
        if (!filepath.equals(null)) {
            if (filepath.contains("/")) {
                return filepath.substring(filepath.lastIndexOf("/") + 1);
            }
        }
        return "";
    }

    @Override
    public Map<String, String> upload() {
        String accessId = ossConfigEntity.getAccessKeyId(); // 请填写您的AccessKeyId。
        String accessKey = ossConfigEntity.getAccessKeySecret(); // 请填写您的AccessKeySecret。
        String endpoint = ossConfigEntity.getEndpoint(); // 请填写您的 endpoint。
        String bucket = ossConfigEntity.getBucketName(); // 请填写您的 bucketname 。
        String host = "http://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        String callbackUrl = ossConfigEntity.getCallbackUrl();
        String dir = makeDir(); // 按年月分配文件夹


//        String accessId = this.accessKeyId; // 请填写您的AccessKeyId。
//        String accessKey = this.accessKeySecret; // 请填写您的AccessKeySecret。
//        String endpoint = this.endpoint; // 请填写您的 endpoint。
//        String bucket = bucketName; // 请填写您的 bucketname 。
//        String host = "http://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
//        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//        String callbackUrl = this.callbackUrl;
//        String dir = this.dir; // 用户上传文件时指定的前缀。

        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        Map<String, String> respMap = new LinkedHashMap<String, String>();
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);


            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));

            JSONObject jasonCallback = new JSONObject();
            jasonCallback.put("callbackUrl", callbackUrl);
            jasonCallback.put("callbackBody",
                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
            respMap.put("callback", base64CallbackBody);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respMap;
    }

    /**
     * 根据当前日期生成目录名称
     *
     * @return
     */
    public String makeDir() {
        LocalDate now = LocalDate.now();
        return now.getYear() + "/" + now.getMonthValue();
    }
}
