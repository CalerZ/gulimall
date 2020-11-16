package com.atguigu.gulimall.gatewall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author Caler_赵康乐
 * @create 2020-10-26 15:16
 * @description :跨域处理
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter getCorsWebFilter(){
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);

        CorsWebFilter corsWebFilter = new CorsWebFilter(configurationSource);
        return corsWebFilter;
    }
}
