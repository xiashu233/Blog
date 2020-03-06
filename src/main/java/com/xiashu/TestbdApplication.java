package com.xiashu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.xiashu.spblog.mapper")
public class TestbdApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestbdApplication.class, args);
    }

}
