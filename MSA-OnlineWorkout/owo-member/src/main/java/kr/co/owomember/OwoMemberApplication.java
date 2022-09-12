package kr.co.owomember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OwoMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwoMemberApplication.class, args);
    }

}
