package kr.co.owomember.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.owocommon.error.model.ResponseFormat;
import kr.co.owomember.domain.dto.OauthDto;
import kr.co.owomember.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OauthController {

    private final OauthService oauthService;

    @ApiOperation("카카오 회원가입")
    @PostMapping("/kakao/login/singUp")
    public ResponseFormat signUpKakao(@RequestBody OauthDto.CREATE create){
        //TODO : Oauth kakao create
        return ResponseFormat.ok();
    }

    @ApiOperation("네이버 회원가입")
    @PostMapping("/naver/login/singUp")
    public ResponseFormat signUpNaver(@RequestBody OauthDto.CREATE create){
        //TODO : Oauth naver create
        return ResponseFormat.ok();
    }

    @ApiOperation("구글 회원가입")
    @PostMapping("/google/login/singUp")
    public ResponseFormat signUpGoogle(@RequestBody OauthDto.CREATE create){
        //TODO : Oauth google create
        return ResponseFormat.ok();
    }

    @ApiOperation("카카오 로그인")
    @PostMapping("/kakao/login")
    public ResponseFormat<OauthDto.TOKEN_READ> loginKAKAO(@RequestBody OauthDto.LOGIN login){
        //TODO : Oauth kakao login
        return ResponseFormat.ok();
    }

    @ApiOperation("네이버 로그인")
    @PostMapping("/naver/login")
    public ResponseFormat<OauthDto.TOKEN_READ> loginNAVER(@RequestBody OauthDto.LOGIN login){
        //TODO : Oauth naver login
        return ResponseFormat.ok();
    }

    @ApiOperation("구글 로그인")
    @PostMapping("/google/login")
    public ResponseFormat<OauthDto.TOKEN_READ> signUpGOOGLE(@RequestBody OauthDto.LOGIN login){
        //TODO : Oauth google login
        return ResponseFormat.ok();
    }
}
