package uz.samir.kuchtopbot;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KuchTopBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(KuchTopBotApplication.class, args);

        Sentry.captureMessage("KuchTopBot ishga tushdi — test xabari");
    }

}
