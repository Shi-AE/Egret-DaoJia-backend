package com.edj.publics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class PublicsApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(PublicsApplication.class)
                .build(args)
                .run(args);
        log.info("""
                 \s
                  ______ _____       _   _____       _     _ _         \s
                 |  ____|  __ \\     | | |  __ \\     | |   | (_)        \s
                 | |__  | |  | |    | | | |__) |   _| |__ | |_  ___ ___\s
                 |  __| | |  | |_   | | |  ___/ | | | '_ \\| | |/ __/ __|
                 | |____| |__| | |__| | | |   | |_| | |_) | | | (__\\__ \\
                 |______|_____/ \\____/  |_|    \\__,_|_.__/|_|_|\\___|___/
                                                                       \s
                 :: 白鹭到家-通用服务启动 ::
                """);
    }
}
