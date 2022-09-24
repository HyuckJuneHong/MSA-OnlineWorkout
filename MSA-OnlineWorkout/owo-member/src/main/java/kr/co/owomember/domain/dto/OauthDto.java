package kr.co.owomember.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
        private String code;
        private String provider;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CREATE {
        @ApiModelProperty(example = "사용할 아이디")
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        @ApiModelProperty(example = "사용할 비밀번호")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        private String name;

        private String provider;

        @ApiModelProperty(example = "ROLE_MEMBER")
        @NotBlank(message = "권한을 입력해주세요.")
        private String memberRole;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TOKEN_READ {
        @ApiModelProperty(example = "사용자 인증을 위한 AccessToken")
        private String accessToken;

        @ApiModelProperty(example = "자동 로그인을 위한 refreshToken")
        private String refreshToken;

        @ApiModelProperty(example = "첫 로그인이면 True, 아니면 False (최초 접속 사용자인지 판별)")
        private boolean isFirst;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TOKEN_INFO {
        private String accessToken;
        private String refreshToken;
        private String tokenType;
        private long accessExpiresIn;
        private long refreshExpiresIn;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PROFILE {
        private String name;
        private String email;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KAKAO {
        private int id;
        private ACCOUNT account;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public class ACCOUNT{
            private String name;
            private String email;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GOOGLE {
        private String name;
        private String email;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NAVER {
        private RESPONSE response;

        @Getter
        @NoArgsConstructor
        public class RESPONSE{
            private String id;
            private String name;
            private String email;
        }
    }
}
