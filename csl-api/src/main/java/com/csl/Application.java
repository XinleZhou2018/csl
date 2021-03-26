package com.csl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.csl", "org.n3r.idworker"})
//// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.csl.mapper", annotationClass = Repository.class)
public class Application {
    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
    }
}
