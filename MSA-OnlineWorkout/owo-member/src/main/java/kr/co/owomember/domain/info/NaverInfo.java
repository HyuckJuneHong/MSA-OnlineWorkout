package kr.co.owomember.domain.info;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NaverInfo {
    private final String googleUrlLogin = "oauth.info.naver.url.login";

    @Value("${oauth.info.naver.client-id}")
    private String naverClientId;

    @Value("${oauth.info.naver.client-secret}")
    private String naverClientSecret;

    @Value("${oauth.info.naver.redirect}")
    private String naverRedirect;

    @Value("${oauth.info.naver.url.token}")
    private String naverUrlToken;

    @Value("${oauth.info.naver.url.profile}")
    private String naverUrlProfile;
}
