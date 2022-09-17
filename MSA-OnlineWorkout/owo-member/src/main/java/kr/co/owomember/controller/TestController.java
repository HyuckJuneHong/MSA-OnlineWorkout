package kr.co.owomember.controller;

import kr.co.owomember.domain.vo.Greeting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final Environment environment;
    private final Greeting greeting;

    @GetMapping("/welcome")
    public String welcome(){
        //TODO 환영 메시지.
//      return environment.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @GetMapping("/health_check")
    public String statusCheck(){
        //TODO 작동 상태 확인.
        return String.format("It's Working in OWO_MEMBER on Port %s"
                , environment.getProperty("local.server.port"));
    }

    @GetMapping("/all")
    public String getAllMembers(){
        //TODO : 전체 사용자 조회.
        return "모든 사용자 조회";
    }
}
