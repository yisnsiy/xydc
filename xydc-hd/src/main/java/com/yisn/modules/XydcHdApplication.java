package com.yisn.modules;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yisn.modules.mapper")
public class XydcHdApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydcHdApplication.class, args);
    }

}
