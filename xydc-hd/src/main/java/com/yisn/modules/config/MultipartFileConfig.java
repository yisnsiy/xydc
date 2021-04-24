package com.yisn.modules.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * Created by yisn on 2021/04/21/23:15
 */
@Configuration
public class MultipartFileConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据最大值 7Mb, 原方法 setMaxFileSize(long maxFileSize) 已经过期,建议使用 setMaxFileSize(DataSize maxFileSize)
        factory.setMaxFileSize(DataSize.ofBytes(1024 * 1024 * 7L));
        /// 总上传数据最大值 14M, 同将 setMaxRequestSize(long maxRequestSize) 方法替换为 setMaxRequestSize(DataSize maxRequestSize)
        factory.setMaxRequestSize(DataSize.ofBytes(1024 * 1024 * 70L));
        // DataSize 方法属性配置建议自行查看源码
        return factory.createMultipartConfig();
    }

}
