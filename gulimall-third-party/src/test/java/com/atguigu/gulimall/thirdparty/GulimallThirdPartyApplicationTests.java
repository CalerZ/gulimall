package com.atguigu.gulimall.thirdparty;

import com.atguigu.gulimall.thirdparty.fileservice.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Autowired
    private FileService fileService;


    @Test
    void contextLoads() {
        Map<String, String> map = fileService.upload();
        System.out.println(map);
    }

}
