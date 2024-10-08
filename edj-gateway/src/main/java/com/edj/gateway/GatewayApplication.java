package com.edj.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author A.E.
 * @date 2024/9/23
 */
@Slf4j
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayApplication.class)
                .build(args)
                .run(args);
        log.info("""
                \s
                  ______ _____       _    _____       _                          \s
                 |  ____|  __ \\     | |  / ____|     | |                         \s
                 | |__  | |  | |    | | | |  __  __ _| |_ _____      ____ _ _   _\s
                 |  __| | |  | |_   | | | | |_ |/ _` | __/ _ \\ \\ /\\ / / _` | | | |
                 | |____| |__| | |__| | | |__| | (_| | ||  __/\\ V  V / (_| | |_| |
                 |______|_____/ \\____/   \\_____|\\__,_|\\__\\___| \\_/\\_/ \\__,_|\\__, |
                                                                             __/ |
                                                                            |___/\s
                 :: 白鹭到家-网关服务 ::
                """);
    }
}
