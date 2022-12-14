package kr.co.owomember.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class MemberDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class LOGIN{
        @ApiModelProperty(example = "userId")
        @NotBlank(message = "아이디를 입력해주세요")
        private String identity;

        @ApiModelProperty(example = "userPw")
        @NotBlank(message = "아이디를 입력해주세요")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class TOKEN{
        @ApiModelProperty(example = "사용자 인증을 위한 accessToken")
        private String accessToken;

        @ApiModelProperty(example = "자동 로그인을 위한 refreshToken")
        private String refreshToken;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class RE_TOKEN{
        @ApiModelProperty(example = "자동 로그인을 위한 refreshToken")
        private String refreshToken;
    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class CREATE_MEMBER{
        @ApiModelProperty(example = "userId")
        @NotBlank(message = "아이디를 입력하세요.")
        private String identity;

        @ApiModelProperty(example = "userPw")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        @ApiModelProperty(example = "userPw")
        @NotBlank(message = "확인 비밀번호를 입력해주세요.")
        private String checkPassword;

        @ApiModelProperty(example = "HongName")
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @ApiModelProperty(example = "ROLE_MEMBER")
        @NotBlank(message = "권한을 입력해주세요.")
        private String memberRole;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UPDATE_MEMBER{
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UPDATE_PASSWORD{
        private String oldPassword;
        private String newPassword;
        private String checkPassword;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ_MEMBER{
        private String identity;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class DELETE_MEMBER{
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class DETAILS_MEMBER {
        private String identity;
        private String name;
        private String memberRole;
    }
}
