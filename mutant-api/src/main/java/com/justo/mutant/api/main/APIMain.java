package com.justo.mutant.api.main;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@Configuration
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan({"com.justo.mutant.api"})
@SpringBootApplication
public class APIMain {
    
    public static void main(String[] args) throws InterruptedException {
        new SpringApplicationBuilder(APIMain.class).bannerMode(Banner.Mode.OFF).run(args);
    }

}