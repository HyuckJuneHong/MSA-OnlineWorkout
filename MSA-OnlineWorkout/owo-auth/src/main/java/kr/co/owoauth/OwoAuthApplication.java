package kr.co.owoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OwoAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwoAuthApplication.class, args);
    }

}
