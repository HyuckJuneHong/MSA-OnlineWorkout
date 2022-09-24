package kr.co.owomember.service;

import kr.co.owomember.domain.dto.OauthDto;
import org.springframework.http.ResponseEntity;

public interface OauthService {

    //login
    OauthDto.TOKEN_READ loginKakao(String accessToken);
    OauthDto.TOKEN_READ loginGoogle(String accessToken);
    OauthDto.TOKEN_READ loginNaver(String accessToken);

    //create
    void signUpKakao(OauthDto.CREATE create);
    void signUpGoogle(OauthDto.CREATE create);
    void signUpNaver(OauthDto.CREATE create);

    //common
    OauthDto.PROFILE checkProfile(ResponseEntity<String> response, String provider);

    //get
    OauthDto.PROFILE getProfile(String accessToken, String provider);
    OauthDto.TOKEN_INFO getAccessToken(OauthDto.LOGIN login);
    OauthDto.REQUEST getOauthInfo(String code, String provider);
    OauthDto.REQUEST kakaoInfo(String code);
    OauthDto.REQUEST googleInfo(String code);
    OauthDto.REQUEST naverInfo(String code);
    String getUrlProfile(String provider);

}
