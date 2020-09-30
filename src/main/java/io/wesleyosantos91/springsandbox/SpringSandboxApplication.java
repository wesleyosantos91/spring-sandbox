package io.wesleyosantos91.springsandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class SpringSandboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSandboxApplication.class, args);
    }

}
