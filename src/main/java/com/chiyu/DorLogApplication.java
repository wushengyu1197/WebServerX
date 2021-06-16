package com.chiyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 */
@EnableAsync//开启异步
@EnableScheduling//开启定时任务
//@SpringBootApplication
@MapperScan("com.chiyu.mapper")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class DorLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(DorLogApplication.class,args);
    }
}