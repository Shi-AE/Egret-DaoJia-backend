package edj.orders.grab;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class OrdersGrabApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OrdersGrabApplication.class)
                .build(args)
                .run(args);
        log.info("""
                  \s
                    ______ _____       _    ____          _                  _____           _    \s
                   |  ____|  __ \\     | |  / __ \\        | |                / ____|         | |   \s
                   | |__  | |  | |    | | | |  | |_ __ __| | ___ _ __ ___  | |  __ _ __ __ _| |__ \s
                   |  __| | |  | |_   | | | |  | | '__/ _` |/ _ \\ '__/ __| | | |_ | '__/ _` | '_ \\\s
                   | |____| |__| | |__| | | |__| | | | (_| |  __/ |  \\__ \\ | |__| | | | (_| | |_) |
                   |______|_____/ \\____/   \\____/|_|  \\__,_|\\___|_|  |___/  \\_____|_|  \\__,_|_.__/\s
                                                                                                  \s
                 :: 白鹭到家 - 抢单服务 ::
                """);

    }
}
