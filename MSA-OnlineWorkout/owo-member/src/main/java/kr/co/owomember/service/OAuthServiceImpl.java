//package kr.co.owomember.service;
//
//import com.google.gson.Gson;
//import kr.co.owocommon.error.exception.BadRequestException;
//import kr.co.owomember.domain.dto.OAuthDto;
//import kr.co.owomember.domain.info.GoogleInfo;
//import kr.co.owomember.domain.info.KakaoInfo;
//import kr.co.owomember.domain.info.NaverInfo;
//import kr.co.owomember.domain.shared.enums.MemberRole;
//import kr.co.owomember.infra.security.jwt.JwtProvider;
//import kr.co.owomember.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//@RequiredArgsConstructor
//public class OAuthServiceImpl implements OAuthService {
//
//    private final KakaoInfo kakaoInfo;
//    private final NaverInfo naverInfo;
//    private final GoogleInfo googleInfo;
//
//    private final MemberRepository memberRepository;
//    private final JwtProvider jwtProvider;
//    private final PasswordEncoder passwordEncoder;
//    private final RedisService redisService;
//    private final RestTemplate restTemplate;
//    private final Gson gson;
//
//    final String GRANT_TYPE = "grant_type";
//    final String AUTHORIZATION_CODE = "authorization_code";
//    final String CLIENT_ID = "client_id";
//    final String CLIENT_SECRET = "client_secret";
//    final String REDIRECT_URI = "redirect_uri";
//    final String CODE = "code";
//    final String KAKAO = "kakao";
//    final String NAVER = "naver";
//    final String GOOGLE = "google";
//
//    @Override
//    public OAuthDto.TOKEN_READ loginKakao(String accessToken) {
//        return null;
//    }
//
//    @Override
//    public OAuthDto.TOKEN_READ loginGoogle(String accessToken) {
//        return null;
//    }
//
//    @Override
//    public OAuthDto.TOKEN_READ loginNaver(String accessToken) {
//        return null;
//    }
//
//    @Override
//    public void signUpKakao(OAuthDto.CREATE create) {
//
//    }
//
//    @Override
//    public void signUpGoogle(OAuthDto.CREATE create) {
//
//    }
//
//    @Override
//    public void signUpNaver(OAuthDto.CREATE create) {
//
//    }
//
//    /**
//     *
//     * @param response
//     * @param provider
//     * @return
//     */
//    @Override
//    public OAuthDto.PROFILE checkProfile(ResponseEntity<String> response, String provider) {
//        if(provider.equals(KAKAO)){
//            OAuthDto.KAKAO kakao = gson.fromJson(response.getBody(), OAuthDto.KAKAO.class);
//            return OAuthDto.PROFILE.builder()
//                    .name(kakao.getAccount().getName())
//                    .email(kakao.getAccount().getEmail())
//                    .build();
//        } else if(provider.equals(GOOGLE)){
//            OAuthDto.GOOGLE google = gson.fromJson(response.getBody(), OAuthDto.GOOGLE.class);
//            return OAuthDto.PROFILE.builder()
//                    .name(google.getName())
//                    .email(google.getEmail())
//                    .build();
//        } else if(provider.equals(NAVER)){
//            OAuthDto.NAVER naver = gson.fromJson(response.getBody(), OAuthDto.NAVER.class);
//            return OAuthDto.PROFILE.builder()
//                    .name(naver.getResponse().getName())
//                    .email(naver.getResponse().getEmail())
//                    .build();
//        } throw new BadRequestException("????????? ?????? ???????????????.");
//    }
//
//    @Override
//    public OAuthDto.PROFILE getProfile(String accessToken, String provider) {
//        HttpHeaders httpHeaders = httpHeader();
//        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
//        String profileUrl = getUrlProfile(provider);
//
//        HttpEntity<MultiValueMap<String, String>> httpEntity
//                = new HttpEntity<>(null, httpHeaders);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(
//                        profileUrl,
//                        httpEntity,
//                        String.class);
//        if(response.getStatusCode() != HttpStatus.OK)
//            throw new BadRequestException("Profile ?????? ??????");
//        return checkProfile(response, provider);
//    }
//
//    /**
//     * loginOauth Method?????? code????????? provider??? ???????????? ?????? Token??? ?????? ????????? ?????????
//     * @param login
//     * @return
//     */
//    @Override
//    public OAuthDto.TOKEN_INFO getAccessToken(OAuthDto.LOGIN login) {
//        HttpHeaders httpHeaders = httpHeader();
//        OAuthDto.REQUEST request = getOauthInfo(login.getCode(), login.getProvider());
//
//        //HTTP??? Header??? Body ????????? ????????? ??????????????? Class
//        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity
//                = new HttpEntity<>(request.getMap(), httpHeaders);
//
//        //RestTemplate.exchange() ????????? ????????? ?????? POST ???????????? ??????.
//        ResponseEntity<String> response = restTemplate.postForEntity(
//                        request.getUrl(),
//                        httpEntity,
//                        String.class);
//
//        if(response.getStatusCode() != HttpStatus.OK)
//            throw new BadRequestException("Token ?????? ??????");
//        return gson.fromJson(response.getBody(), OAuthDto.TOKEN_INFO.class);
//    }
//
//    /**
//     * ?????? ?????? ??????
//     * @param code
//     * @param provider
//     * @return
//     */
//    @Override
//    public OAuthDto.REQUEST getOauthInfo(String code, String provider) {
//        if (provider.equals(KAKAO)) {
//            return kakaoInfo(code);
//        } else if (provider.equals(GOOGLE)) {
//            return googleInfo(code);
//        } else if (provider.equals(NAVER)) {
//            return naverInfo(code);
//        } throw new BadRequestException("?????? ?????? ???????????? ????????????.");
//    }
//
//    /**
//     * Profile Url ??????
//     * @param provider
//     * @return
//     */
//    @Override
//    public String getUrlProfile(String provider) {
//        if(provider.equals(KAKAO)){
//            return kakaoInfo.getKakaoUrlProfile();
//        }else if(provider.equals(GOOGLE)){
//            return googleInfo.getGoogleUrlProfile();
//        }else if(provider.equals(NAVER)){
//            return naverInfo.getNaverUrlProfile();
//        } throw new BadRequestException("????????? ?????? ????????? ???????????????.");
//    }
//
//    /**
//     * Kakao ??????
//     * @param code
//     * @return
//     */
//    @Override
//    public OAuthDto.REQUEST kakaoInfo(String code) {
//        //MultiValueMap : JSON ???????????? ??????????????? ????????? ??????
//        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add(GRANT_TYPE, AUTHORIZATION_CODE);
//        map.add(CLIENT_ID, kakaoInfo.getKakaoClientId());
//        map.add(REDIRECT_URI, kakaoInfo.getKakaoRedirect());
//        map.add(CODE, code);
//        return OAuthDto.REQUEST.builder()
//                .url(kakaoInfo.getKakaoUrlToken())
//                .map(map)
//                .build();
//    }
//
//    /**
//     * google ??????
//     * @param code
//     * @return
//     */
//    @Override
//    public OAuthDto.REQUEST googleInfo(String code) {
//        //MultiValueMap : JSON ???????????? ??????????????? ????????? ??????
//        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add(GRANT_TYPE, AUTHORIZATION_CODE);
//        map.add(CLIENT_ID, googleInfo.getGoogleClientId());
//        map.add(CLIENT_SECRET, googleInfo.getGoogleClientSecret());
//        map.add(REDIRECT_URI, googleInfo.getGoogleRedirect());
//        map.add(CODE, code);
//        return OAuthDto.REQUEST.builder()
//                .url(googleInfo.getGoogleUrlToken())
//                .map(map)
//                .build();
//    }
//
//    /**
//     * Naver ??????
//     * @param code
//     * @return
//     */
//    @Override
//    public OAuthDto.REQUEST naverInfo(String code) {
//        //MultiValueMap : JSON ???????????? ??????????????? ????????? ??????
//        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add(GRANT_TYPE, AUTHORIZATION_CODE);
//        map.add(CLIENT_ID, naverInfo.getNaverClientId());
//        map.add(CLIENT_SECRET, naverInfo.getNaverClientSecret());
//        map.add(REDIRECT_URI, naverInfo.getNaverRedirect());
//        map.add(CODE, code);
//        return OAuthDto.REQUEST.builder()
//                .url(naverInfo.getNaverUrlToken())
//                .map(map)
//                .build();
//    }
//
//    /**
//     * HttpHeader : HTTP??? Header??? ??????????????? Class
//     * MediaType.APPLICATION_FORM_URLENCODED
//     *  -> RestTemplate POST ?????? ??? ????????????(application/x-www-form-urlencoded)
//     * @return
//     */
//    private HttpHeaders httpHeader(){
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        return httpHeaders;
//    }
//
//    /**
//     * OAuth?????? ????????? AccessToken RefreshToken ?????? ?????????
//     * @param identity
//     * @param name
//     * @param isFirst
//     * @return
//     */
//    private OAuthDto.TOKEN_READ generateToken(String identity, String name, boolean isFirst){
//        String accessToken = jwtProvider.createAccessToken(identity, MemberRole.ROLE_MEMBER, name);
//        String refreshToken = jwtProvider.createRefreshToken(identity, MemberRole.ROLE_MEMBER, name);
//
//        return OAuthDto.TOKEN_READ.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .isFirst(isFirst)
//                .build();
//    }
//}
