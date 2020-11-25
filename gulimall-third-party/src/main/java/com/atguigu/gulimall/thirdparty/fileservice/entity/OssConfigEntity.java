package com.atguigu.gulimall.thirdparty.fileservice.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caler_赵康乐
 * @create 2020-11-21 17:32
 * @description :
 */
@Data
@ConfigurationProperties(prefix = "oss")
public class OssConfigEntity {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String callbackUrl;
    private String dir;
}
