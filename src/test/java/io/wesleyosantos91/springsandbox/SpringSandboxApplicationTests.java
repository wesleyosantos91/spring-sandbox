package io.wesleyosantos91.springsandbox;

import io.wesleyosantos91.springsandbox.config.MysqlTestLifecycleManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSandboxApplicationTests extends MysqlTestLifecycleManager {

    @Test
    void contextLoads() {
    }

}
