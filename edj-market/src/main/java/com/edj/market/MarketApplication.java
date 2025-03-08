package com.edj.market;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@SpringBootApplication
public class MarketApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MarketApplication.class)
                .build(args)
                .run(args);
        log.info("""
                  \s
                    ______ _____       _   __  __            _        _  \s
                   |  ____|  __ \\     | | |  \\/  |          | |      | | \s
                   | |__  | |  | |    | | | \\  / | __ _ _ __| | _____| |_\s
                   |  __| | |  | |_   | | | |\\/| |/ _` | '__| |/ / _ \\ __|
                   | |____| |__| | |__| | | |  | | (_| | |  |   <  __/ |_\s
                   |______|_____/ \\____/  |_|  |_|\\__,_|_|  |_|\\_\\___|\\__|
                                                                         \s
                 :: 白鹭到家-优惠券服务 ::
                """);

    }
}
