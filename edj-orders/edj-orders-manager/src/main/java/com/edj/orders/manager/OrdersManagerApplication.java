package com.edj.orders.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class OrdersManagerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OrdersManagerApplication.class)
                .build(args)
                .run(args);
        log.info("""
                  \s
                    ______ _____       _    ____          _                 __  __                                  \s
                   |  ____|  __ \\     | |  / __ \\        | |               |  \\/  |                                 \s
                   | |__  | |  | |    | | | |  | |_ __ __| | ___ _ __ ___  | \\  / | __ _ _ __   __ _  __ _  ___ _ __\s
                   |  __| | |  | |_   | | | |  | | '__/ _` |/ _ \\ '__/ __| | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|
                   | |____| |__| | |__| | | |__| | | | (_| |  __/ |  \\__ \\ | |  | | (_| | | | | (_| | (_| |  __/ |  \s
                   |______|_____/ \\____/   \\____/|_|  \\__,_|\\___|_|  |___/ |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|  \s
                                                                                                      __/ |         \s
                                                                                                     |___/          \s
                                                                                    \s
                 :: 白鹭到家 - 订单管理服务 ::
                """);

    }
}
