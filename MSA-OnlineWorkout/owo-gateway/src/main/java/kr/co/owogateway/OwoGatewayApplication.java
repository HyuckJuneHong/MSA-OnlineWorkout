package kr.co.owogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OwoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwoGatewayApplication.class, args);
    }

}
