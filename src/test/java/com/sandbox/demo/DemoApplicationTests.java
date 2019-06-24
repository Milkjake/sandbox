package com.sandbox.demo;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CustomPostgresqlContainer.getInstance();

    @Test
    public void test() {
        System.out.println("running");
    }
}
