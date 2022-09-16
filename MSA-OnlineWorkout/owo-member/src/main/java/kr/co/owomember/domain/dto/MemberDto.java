package kr.co.owomember.domain.dto;

import io.swagger.annotations.Api;
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
        //TODO : ThreadLocal 미적용 update RefreshToken
        private String identity;

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
        //TODO : ThreadLocal 미적용 update member
        private String identity;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UPDATE_PASSWORD{
        //TODO : ThreadLocal 미적용 update password
        private String identity;
        private String oldPassword;
        private String newPassword;
        private String checkPassword;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ_MEMBER{
        //TODO : ThreadLocal 미적용 Read
        private String identity;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class DELETE_MEMBER{
        //TODO : ThreadLocal 미적용 Delete
        private String identity;
        private String password;
    }
}
