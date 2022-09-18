package kr.co.owocommon.error.jwt;

import kr.co.owocommon.error.exception.BusinessLogicException;

public class JwtTokenExpiredException extends BusinessLogicException {
    public JwtTokenExpiredException() {
        super("JWT Token Expired");
    }
}
