package com.isjingjing.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @authors:静静
 * @description:null
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.isjingjing")
public class VodApplications {
    public static void main(String[] args) {
        SpringApplication.run(VodApplications.class, args);
    }
}
