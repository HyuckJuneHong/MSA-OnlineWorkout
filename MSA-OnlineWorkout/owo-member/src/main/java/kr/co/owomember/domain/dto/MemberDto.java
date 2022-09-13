package kr.co.owomember.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
    public static class CREATE_MEMBER{
        //TODO member create
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
    public static class GET_MEMBER{
        //TODO member get
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class DELETE_MEMBER{
        //TODO member delete
    }
}
