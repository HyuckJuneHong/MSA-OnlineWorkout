package kr.co.owocommon.error.exception;

import kr.co.owocommon.error.model.ErrorCode;

public class DuplicatedException extends BusinessLogicException{
    public DuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
