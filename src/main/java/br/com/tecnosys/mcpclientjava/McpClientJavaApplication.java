package br.com.tecnosys.mcpclientjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class McpClientJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpClientJavaApplication.class, args);
    }

}