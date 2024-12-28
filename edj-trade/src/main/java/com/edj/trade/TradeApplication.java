package com.edj.trade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class TradeApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TradeApplication.class)
                .build(args)
                .run(args);
        log.info("""
                  \s
                 ______ _____       _   _______            _     \s
                |  ____|  __ \\     | | |__   __|          | |    \s
                | |__  | |  | |    | |    | |_ __ __ _  __| | ___\s
                |  __| | |  | |_   | |    | | '__/ _` |/ _` |/ _ \\
                | |____| |__| | |__| |    | | | | (_| | (_| |  __/
                |______|_____/ \\____/     |_|_|  \\__,_|\\__,_|\\___|
                                                                 \s
                 :: 白鹭到家 - 支付服务 ::
                """);
    }
}