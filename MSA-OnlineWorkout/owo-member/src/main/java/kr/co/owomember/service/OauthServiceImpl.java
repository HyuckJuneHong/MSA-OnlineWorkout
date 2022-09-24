package kr.co.owomember.service;

import com.google.gson.Gson;
import kr.co.owocommon.error.exception.BadRequestException;
import kr.co.owomember.domain.dto.OauthDto;
import kr.co.owomember.domain.info.GoogleInfo;
import kr.co.owomember.domain.info.KakaoInfo;
import kr.co.owomember.domain.info.NaverInfo;
import kr.co.owomember.domain.shared.enums.MemberRole;
import kr.co.owomember.infra.security.jwt.JwtProvider;
import kr.co.owomember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{

    private final KakaoInfo kakaoInfo;
    private final GoogleInfo googleInfo;
    private final NaverInfo naverInfo;

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final RestTemplate restTemplate;
    private final Gson gson;

    final String GRANT_TYPE = "grant_type";
    final String AUTHORIZATION_CODE = "authorization_code";
    final String CLIENT_ID = "client_id";
    final String CLIENT_SECRET = "client_secret";
    final String REDIRECT_URI = "redirect_uri";
    final String CODE = "code";
    final String KAKAO = "kakao";
    final String NAVER = "naver";
    final String GOOGLE = "google";

    @Override
    public OauthDto.TOKEN_READ loginKakao(String accessToken) {
        return null;
    }

    @Override
    public OauthDto.TOKEN_READ loginGoogle(String accessToken) {
        return null;
    }

    @Override
    public OauthDto.TOKEN_READ loginNaver(String accessToken) {
        return null;
    }

    @Override
    public void signUpKakao(OauthDto.CREATE create) {

    }

    @Override
    public void signUpGoogle(OauthDto.CREATE create) {

    }

    @Override
    public void signUpNaver(OauthDto.CREATE create) {

    }

    /**
     *
     * @param response
     * @param provider
     * @return
     */
    @Override
    public OauthDto.PROFILE checkProfile(ResponseEntity<String> response, String provider) {
        if(provider.equals(KAKAO)){
            OauthDto.KAKAO kakao = gson.fromJson(response.getBody(), OauthDto.KAKAO.class);
            return OauthDto.PROFILE.builder()
                    .name(kakao.getAccount().getName())
                    .email(kakao.getAccount().getEmail())
                    .build();
        } else if(provider.equals(GOOGLE)){
            OauthDto.GOOGLE google = gson.fromJson(response.getBody(), OauthDto.GOOGLE.class);
            return OauthDto.PROFILE.builder()
                    .name(google.getName())
                    .email(google.getEmail())
                    .build();
        } else if(provider.equals(NAVER)){
            OauthDto.NAVER naver = gson.fromJson(response.getBody(), OauthDto.NAVER.class);
            return OauthDto.PROFILE.builder()
                    .name(naver.getResponse().getName())
                    .email(naver.getResponse().getEmail())
                    .build();
        } throw new BadRequestException("잘못된 소셜 요청입니다.");
    }

    @Override
    public OauthDto.PROFILE getProfile(String accessToken, String provider) {
        HttpHeaders httpHeaders = httpHeader();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        String profileUrl = getUrlProfile(provider);

        HttpEntity<MultiValueMap<String, String>> httpEntity
                = new HttpEntity<>(null, httpHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity(
                        profileUrl,
                        httpEntity,
                        String.class);
        if(response.getStatusCode() != HttpStatus.OK)
            throw new BadRequestException("Profile 발급 실패");
        return checkProfile(response, provider);
    }

    /**
     * loginOauth Method에서 code값이랑 provider가 보내주면 해당 Token을 발급 해주는 메소드
     * @param login
     * @return
     */
    @Override
    public OauthDto.TOKEN_INFO getAccessToken(OauthDto.LOGIN login) {
        HttpHeaders httpHeaders = httpHeader();
        OauthDto.REQUEST request = getOauthInfo(login.getCode(), login.getProvider());

        //HTTP의 Header와 Body 부분을 하나로 저장해주는 Class
        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity
                = new HttpEntity<>(request.getMap(), httpHeaders);

        //RestTemplate.exchange() 사용해 형식에 맞는 POST 방식으로 요청.
        ResponseEntity<String> response = restTemplate.postForEntity(
                        request.getUrl(),
                        httpEntity,
                        String.class);

        if(response.getStatusCode() != HttpStatus.OK)
            throw new BadRequestException("Token 발급 실패");
        return gson.fromJson(response.getBody(), OauthDto.TOKEN_INFO.class);
    }

    /**
     * 소셜 정보 반환
     * @param code
     * @param provider
     * @return
     */
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

    /**
     * Profile Url 반환
     * @param provider
     * @return
     */
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

    /**
     * Kakao 정보
     * @param code
     * @return
     */
    @Override
    public OauthDto.REQUEST kakaoInfo(String code) {
        //MultiValueMap : JSON 형식으로 만들어주기 적합한 타입
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GRANT_TYPE, AUTHORIZATION_CODE);
        map.add(CLIENT_ID, kakaoInfo.getKakaoClientId());
        map.add(REDIRECT_URI, kakaoInfo.getKakaoRedirect());
        map.add(CODE, code);
        return OauthDto.REQUEST.builder()
                .url(kakaoInfo.getKakaoUrlToken())
                .map(map)
                .build();
    }

    /**
     * google 정보
     * @param code
     * @return
     */
    @Override
    public OauthDto.REQUEST googleInfo(String code) {
        //MultiValueMap : JSON 형식으로 만들어주기 적합한 타입
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GRANT_TYPE, AUTHORIZATION_CODE);
        map.add(CLIENT_ID, googleInfo.getGoogleClientId());
        map.add(CLIENT_SECRET, googleInfo.getGoogleClientSecret());
        map.add(REDIRECT_URI, googleInfo.getGoogleRedirect());
        map.add(CODE, code);
        return OauthDto.REQUEST.builder()
                .url(googleInfo.getGoogleUrlToken())
                .map(map)
                .build();
    }

    /**
     * Naver 정보
     * @param code
     * @return
     */
    @Override
    public OauthDto.REQUEST naverInfo(String code) {
        //MultiValueMap : JSON 형식으로 만들어주기 적합한 타입
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GRANT_TYPE, AUTHORIZATION_CODE);
        map.add(CLIENT_ID, naverInfo.getNaverClientId());
        map.add(CLIENT_SECRET, naverInfo.getNaverClientSecret());
        map.add(REDIRECT_URI, naverInfo.getNaverRedirect());
        map.add(CODE, code);
        return OauthDto.REQUEST.builder()
                .url(naverInfo.getNaverUrlToken())
                .map(map)
                .build();
    }

    /**
     * HttpHeader : HTTP의 Header를 만들어주는 Class
     * MediaType.APPLICATION_FORM_URLENCODED
     *  -> RestTemplate POST 전송 시 파라미터(application/x-www-form-urlencoded)
     * @return
     */
    private HttpHeaders httpHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpHeaders;
    }

    /**
     * OAuth에서 사용할 AccessToken RefreshToken 발급 메소드
     * @param identity
     * @param name
     * @param isFirst
     * @return
     */
    private OauthDto.TOKEN_READ generateToken(String identity, String name, boolean isFirst){
        String accessToken = jwtProvider.createAccessToken(identity, MemberRole.ROLE_MEMBER, name);
        String refreshToken = jwtProvider.createRefreshToken(identity, MemberRole.ROLE_MEMBER, name);

        return OauthDto.TOKEN_READ.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isFirst(isFirst)
                .build();
    }
}
