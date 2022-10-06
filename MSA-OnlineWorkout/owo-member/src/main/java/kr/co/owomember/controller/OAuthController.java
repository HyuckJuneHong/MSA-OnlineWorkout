//package kr.co.owomember.controller;
//
//import io.swagger.annotations.ApiModelProperty;
//import io.swagger.annotations.ApiOperation;
//import kr.co.owocommon.error.model.ResponseFormat;
//import kr.co.owomember.domain.dto.OAuthDto;
//import kr.co.owomember.service.OAuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/oauth")
//public class OAuthController {
//
//    private final OAuthService oauthService;
//
//    @ApiModelProperty("로그인 성공")
//    @GetMapping("/naver/success")
//    public String loginSuccess(){
//        return "naver login success";
//    }
//    @GetMapping("/kakao/callback")
//    public String kakaoCallback(String code){
//        return "code: " + code;
//    }
//
//    @ApiOperation("카카오 회원가입")
//    @PostMapping("/kakao/login/singUp")
//    public ResponseFormat signUpKakao(@RequestBody OAuthDto.CREATE create){
//        //TODO : Oauth kakao create
//        return ResponseFormat.ok();
//    }
//
//    @ApiOperation("네이버 회원가입")
//    @PostMapping("/naver/login/singUp")
//    public ResponseFormat signUpNaver(@RequestBody OAuthDto.CREATE create){
//        //TODO : Oauth naver create
//        return ResponseFormat.ok();
//    }
//
//    @ApiOperation("구글 회원가입")
//    @PostMapping("/google/login/singUp")
//    public ResponseFormat signUpGoogle(@RequestBody OAuthDto.CREATE create){
//        //TODO : Oauth google create
//        return ResponseFormat.ok();
//    }
//
//    @ApiOperation("카카오 로그인")
//    @PostMapping("/kakao/login")
//    public ResponseFormat<OAuthDto.TOKEN_READ> loginKAKAO(@RequestBody OAuthDto.LOGIN login){
//        //TODO : Oauth kakao login
//        return ResponseFormat.ok();
//    }
//
//    @ApiOperation("네이버 로그인")
//    @PostMapping("/naver/login")
//    public ResponseFormat<OAuthDto.TOKEN_READ> loginNAVER(@RequestBody OAuthDto.LOGIN login){
//        //TODO : Oauth naver login
//        return ResponseFormat.ok();
//    }
//
//    @ApiOperation("구글 로그인")
//    @PostMapping("/google/login")
//    public ResponseFormat<OAuthDto.TOKEN_READ> signUpGOOGLE(@RequestBody OAuthDto.LOGIN login){
//        //TODO : Oauth google login
//        return ResponseFormat.ok();
//    }
//}
