package kr.co.owomember.service;

import kr.co.owomember.domain.dto.OauthDto;

public interface OauthService {

    //get
    OauthDto.REQUEST getOauthInfo(String code, String provider);
    OauthDto.REQUEST kakaoInfo(String code);
    OauthDto.REQUEST googleInfo(String code);
    OauthDto.REQUEST naverInfo(String code);
    String getUrlProfile(String provider);

}
