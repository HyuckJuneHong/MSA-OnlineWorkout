package kr.co.owoeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class OwoEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwoEurekaApplication.class, args);
    }

}
