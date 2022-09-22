package kr.co.owomember.service;

import kr.co.owomember.domain.dto.OauthRequestDto;

public interface OauthService {

    //get
    OauthRequestDto getOauthInfo(String code, String provider);
    OauthRequestDto kakaoInfo(String code);
    OauthRequestDto googleInfo(String code);
    OauthRequestDto naverInfo(String code);
    String getUrlProfile(String provider);

}
