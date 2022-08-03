package com.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* 相当于SSM中在Spring配置文件中配置了MapperScannerConfigurer
* 指定扫描的包，将包中的Mapper接口注册到容器中
* */
@MapperScan("com.mybatisplus.mapper")
@SpringBootApplication
public class EntryApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EntryApplication.class, args);
    }
    
}
