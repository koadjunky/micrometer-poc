package eu.malycha.micrometerpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class MicrometerPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrometerPocApplication.class, args);
    }
}
