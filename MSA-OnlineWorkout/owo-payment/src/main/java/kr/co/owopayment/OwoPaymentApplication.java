package kr.co.owopayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OwoPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwoPaymentApplication.class, args);
    }

}
