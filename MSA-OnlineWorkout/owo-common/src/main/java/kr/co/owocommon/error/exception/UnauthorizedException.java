package kr.co.owocommon.error.exception;

import kr.co.owocommon.error.model.ErrorCode;

public class UnauthorizedException extends BusinessLogicException{
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED_USER);
    }
}