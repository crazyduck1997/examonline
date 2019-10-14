package com.qf.examonline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.qf.examonline.dao")
@SpringBootApplication
public class ExamonlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamonlineApplication.class, args);
    }

}
