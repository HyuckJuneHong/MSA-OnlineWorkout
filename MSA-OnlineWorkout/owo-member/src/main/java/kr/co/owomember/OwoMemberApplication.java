package kr.co.owomember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient //Eureka Server 등록 위함.
public class OwoMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwoMemberApplication.class, args);
    }

}
