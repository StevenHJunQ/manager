package com.aoxing.it;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author steven
 */
@SpringBootApplication
@MapperScan("com.aoxing.it.mapper")
public class ItApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItApplication.class, args);
    }

}
