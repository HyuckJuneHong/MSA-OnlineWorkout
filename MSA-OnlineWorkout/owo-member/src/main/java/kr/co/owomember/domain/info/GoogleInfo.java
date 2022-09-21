package kr.co.owomember.domain.info;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GoogleInfo {

    private final String googleUrlLogin = "oauth.info.google.url.login";

    @Value("${oauth.info.google.client-id}")
    private String googleClientId;

    @Value("${oauth.info.google.client-secret}")
    private String googleClientSecret;

    @Value("${oauth.info.google.redirect}")
    private String googleRedirect;

    @Value("${oauth.info.google.url.token}")
    private String googleUrlToken;

    @Value("${oauth.info.google.url.profile}")
    private String googleUrlProfile;
}
