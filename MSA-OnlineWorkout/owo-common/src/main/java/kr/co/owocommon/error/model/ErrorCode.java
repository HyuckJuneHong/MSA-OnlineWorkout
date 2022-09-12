package kr.co.owocommon.error.model;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //member
    WRONG_PASSWORD("비밀번호를 다시 확인해주세요", 400),
    DUPLICATED_ID("중복된 아이디를 사용할 수 없습니다.", 400);

    private String message;
    private int status;

    ErrorCode(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
