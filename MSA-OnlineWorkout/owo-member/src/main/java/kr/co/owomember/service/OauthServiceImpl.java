package kr.co.owomember.service;

import kr.co.owocommon.error.exception.BadRequestException;
import kr.co.owomember.domain.dto.OauthDto;
import kr.co.owomember.domain.info.GoogleInfo;
import kr.co.owomember.domain.info.KakaoInfo;
import kr.co.owomember.domain.info.NaverInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{

    private final KakaoInfo kakaoInfo;
    private final GoogleInfo googleInfo;
    private final NaverInfo naverInfo;

    final String GRANT = "grant_type";
    final String AUTHORIZATION = "authorization_code";
    final String CLIENT_ID = "client_id";
    final String CLIENT_SECRET = "client_secret";
    final String REDIRECT = "redirect_uri";
    final String CODE = "code";
    final String KAKAO = "kakao";
    final String NAVER = "naver";
    final String GOOGLE = "google";

    @Override
    public OauthDto.REQUEST getOauthInfo(String code, String provider) {
        if (provider.equals(KAKAO)) {
            return kakaoInfo(code);
        } else if (provider.equals(GOOGLE)) {
            return googleInfo(code);
        } else if (provider.equals(NAVER)) {
            return naverInfo(code);
        } throw new BadRequestException("해당 소셜 로그인은 없습니다.");
    }

    @Override
    public String getUrlProfile(String provider) {
        if(provider.equals(KAKAO)){
            return kakaoInfo.getKakaoUrlProfile();
        }else if(provider.equals(GOOGLE)){
            return googleInfo.getGoogleUrlProfile();
        }else if(provider.equals(NAVER)){
            return naverInfo.getNaverUrlProfile();
        } throw new BadRequestException("잘못된 소셜 프로필 요청입니다.");
    }

    @Override
    public OauthDto.REQUEST kakaoInfo(String code) {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GRANT, AUTHORIZATION);
        map.add(CLIENT_ID, kakaoInfo.getKakaoClientId());
        map.add(REDIRECT, kakaoInfo.getKakaoRedirect());
        map.add(CODE, code);
        return OauthDto.REQUEST.builder()
                .url(kakaoInfo.getKakaoUrlToken())
                .map(map)
                .build();
    }

    @Override
    public OauthDto.REQUEST googleInfo(String code) {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GRANT, AUTHORIZATION);
        map.add(CLIENT_ID, googleInfo.getGoogleClientId());
        map.add(CLIENT_SECRET, googleInfo.getGoogleClientSecret());
        map.add(REDIRECT, googleInfo.getGoogleRedirect());
        map.add(CODE, code);
        return OauthDto.REQUEST.builder()
                .url(googleInfo.getGoogleUrlToken())
                .map(map)
                .build();
    }

    @Override
    public OauthDto.REQUEST naverInfo(String code) {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GRANT, AUTHORIZATION);
        map.add(CLIENT_ID, naverInfo.getNaverClientId());
        map.add(CLIENT_SECRET, naverInfo.getNaverClientSecret());
        map.add(REDIRECT, naverInfo.getNaverRedirect());
        map.add(CODE, code);
        return OauthDto.REQUEST.builder()
                .url(naverInfo.getNaverUrlToken())
                .map(map)
                .build();
    }
}
