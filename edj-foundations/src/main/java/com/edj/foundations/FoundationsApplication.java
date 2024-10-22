package com.edj.foundations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@SpringBootApplication
public class FoundationsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FoundationsApplication.class)
                .build(args)
                .run(args);
        log.info("""
                  \s
                  ______ _____       _   ______                    _       _   _                \s
                 |  ____|  __ \\     | | |  ____|                  | |     | | (_)               \s
                 | |__  | |  | |    | | | |__ ___  _   _ _ __   __| | __ _| |_ _  ___  _ __  ___\s
                 |  __| | |  | |_   | | |  __/ _ \\| | | | '_ \\ / _` |/ _` | __| |/ _ \\| '_ \\/ __|
                 | |____| |__| | |__| | | | | (_) | |_| | | | | (_| | (_| | |_| | (_) | | | \\__ \\
                 |______|_____/ \\____/  |_|  \\___/ \\__,_|_| |_|\\__,_|\\__,_|\\__|_|\\___/|_| |_|___/
                                                                                                \s
                 :: 白鹭到家-运营基础服务 ::
                """);

    }
}
