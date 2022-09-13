package kr.co.owogateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/gateway")
@RestController
public class GatewayController {

    @GetMapping
    public String test(){
        return "gateway-test";
    }
}
