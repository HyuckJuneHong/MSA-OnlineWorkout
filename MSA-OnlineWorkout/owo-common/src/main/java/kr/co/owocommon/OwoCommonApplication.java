package kr.co.owocommon;

import kr.co.owocommon.error.model.FeignErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OwoCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwoCommonApplication.class, args);
    }

    @Bean
    public FeignErrorDecoder getFeignErrorDecoder(){
        return new FeignErrorDecoder();
    }
}
