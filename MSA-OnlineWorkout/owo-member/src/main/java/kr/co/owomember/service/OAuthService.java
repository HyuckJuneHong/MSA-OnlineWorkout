package kr.co.owomember.service;

import kr.co.owomember.domain.dto.OAuthDto;
import org.springframework.http.ResponseEntity;

public interface OAuthService {

    //login
    OAuthDto.TOKEN_READ loginKakao(String accessToken);
    OAuthDto.TOKEN_READ loginGoogle(String accessToken);
    OAuthDto.TOKEN_READ loginNaver(String accessToken);

    //create
    void signUpKakao(OAuthDto.CREATE create);
    void signUpGoogle(OAuthDto.CREATE create);
    void signUpNaver(OAuthDto.CREATE create);

    //common
    OAuthDto.PROFILE checkProfile(ResponseEntity<String> response, String provider);

    //get
    OAuthDto.PROFILE getProfile(String accessToken, String provider);
    OAuthDto.TOKEN_INFO getAccessToken(OAuthDto.LOGIN login);
    OAuthDto.REQUEST getOauthInfo(String code, String provider);
    OAuthDto.REQUEST kakaoInfo(String code);
    OAuthDto.REQUEST googleInfo(String code);
    OAuthDto.REQUEST naverInfo(String code);
    String getUrlProfile(String provider);

}
