package com.manjusaka.redis.springbootredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableCaching
@ComponentScan(basePackages = "com.manjusaka.redis.springbootredis.*")
@ServletComponentScan(basePackages = "com.manjusaka.redis.springbootredis.config.*")
@SpringBootApplication
public class SpringbootredisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootredisApplication.class, args);
    }
}
