package io.wesleyosantos91.springsandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableCircuitBreaker
@EnableSpringDataWebSupport
@EnableCaching
@SpringBootApplication
public class SpringSandboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSandboxApplication.class, args);
    }

}
