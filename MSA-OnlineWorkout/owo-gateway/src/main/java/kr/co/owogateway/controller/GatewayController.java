package kr.co.owogateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GatewayController {

    private final Environment environment;

    @GetMapping
    public String test(){
        return "gateway-test";
    }

    @GetMapping("/config")
    public String configCheck(){
        return String.format("It's Working in Gateway Service %s \n %s \n %s \n %s"
                , environment.getProperty("local.server.port")
                , environment.getProperty("server.port")
                , environment.getProperty("jwt.secret")
                , environment.getProperty("jwt.description"));
    }

}
