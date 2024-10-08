package com.edj.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserApplication.class)
                .build(args)
                .run(args);
        log.info("""
                  \s
                  ______ _____       _   _    _              \s
                 |  ____|  __ \\     | | | |  | |             \s
                 | |__  | |  | |    | | | |  | |___  ___ _ __\s
                 |  __| | |  | |_   | | | |  | / __|/ _ \\ '__|
                 | |____| |__| | |__| | | |__| \\__ \\  __/ |  \s
                 |______|_____/ \\____/   \\____/|___/\\___|_|  \s
                                                             \s
                                                             \s
                 :: 白鹭到家-用户服务 ::
                """);

    }
}
