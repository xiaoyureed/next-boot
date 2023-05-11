package io.github.xiaoyureed.nextbootslim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

@SpringBootApplication(exclude = FlywayAutoConfiguration.class)
public class NextBoot {

    public static void main(String[] args) {
        SpringApplication.run(NextBoot.class, args);
    }
}
