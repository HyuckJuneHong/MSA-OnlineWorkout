package kr.co.owocommon.error.jwt;

import kr.co.owocommon.error.exception.UserDefineException;

public class JwtTokenInvalidException extends UserDefineException {
    public JwtTokenInvalidException(String message) {
        super(message);
    }
}
