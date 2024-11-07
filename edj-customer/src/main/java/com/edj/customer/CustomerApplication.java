package com.edj.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CustomerApplication.class)
                .build(args)
                .run(args);
        log.info("""
                  \s
                 ______ _____       _    _____          _                           \s
                |  ____|  __ \\     | |  / ____|        | |                          \s
                | |__  | |  | |    | | | |    _   _ ___| |_ ___  _ __ ___   ___ _ __\s
                |  __| | |  | |_   | | | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__|
                | |____| |__| | |__| | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \s
                |______|_____/ \\____/   \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  \s
                                                                                    \s
                 :: 白鹭到家 - 客户中心服务 ::
                """);

    }
}
