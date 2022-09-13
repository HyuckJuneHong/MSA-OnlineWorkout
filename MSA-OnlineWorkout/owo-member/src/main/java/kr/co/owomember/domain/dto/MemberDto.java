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
        //TODO login
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class TOKEN{
        //TODO token create
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

        @ApiModelProperty(example = "홍길동")
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
        //TODO member update
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UPDATE_PASSWORD{
        //TODO member pw update
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ_MEMBER{
        //TODO member read
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class DELETE_MEMBER{
        //TODO member delete
    }
}
