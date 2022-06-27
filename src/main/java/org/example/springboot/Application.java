package org.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing // Jpa Auditing 활성화
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 이걸 동작하면 내장 WAS를 사용할 수 있다.
    }

}
