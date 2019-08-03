package com.matrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author DuFeng
 * @date 2018/07/11 10:01:46
 */
@Configuration
@EnableCaching
@SpringBootApplication
@ServletComponentScan( basePackages = { "com.matrix.common.filter" } )
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@PropertySource("classpath:config.properties")
public class MatrixApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MatrixApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MatrixApplication.class, args);
    }
}
