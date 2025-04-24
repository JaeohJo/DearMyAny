package org.example.dearmyany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DearMyAnyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DearMyAnyApplication.class, args);
    }

}
