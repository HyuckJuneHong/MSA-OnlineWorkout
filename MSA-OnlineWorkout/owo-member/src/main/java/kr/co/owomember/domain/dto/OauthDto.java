package kr.co.owomember.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;

public class OauthDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class REQUEST {
        private String url;
        private LinkedMultiValueMap<String, String> map;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LOGIN {
        //TODO Oauth Login
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CREATE {
        //TODO Oauth Create
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TOKEN_READ {
        //TODO Oauth Token read
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TOKEN_INFO {
        //TODO Oauth Token info
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PROFILE {
        //TODO Oauth Profile
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class KAKAO {
        //TODO Oauth Kakao

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ACCOUNT{
            //TODO Oauth Account
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Google {
        //TODO Oauth Google
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class NAVER {
        //TODO Oauth Naver

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class RESPONSE{
            //TODO Oauth Response
        }
    }
}
