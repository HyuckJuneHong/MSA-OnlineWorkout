package kr.co.owomember.domain.info;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoInfo {

    private final String googleUrlLogin = "oauth.info.kakao.url.login";

    @Value("${oauth.info.kakao.client-id}")
    private String kakaoClientId;

    @Value("${oauth.info.kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${oauth.info.kakao.redirect}")
    private String kakaoRedirect;

    @Value("${oauth.info.kakao.url.token}")
    private String kakaoUrlToken;

    @Value("${oauth.info.kakao.url.profile}")
    private String kakaoUrlProfile;
}
