package kr.co.owocommon.error.exception;

import kr.co.owocommon.error.model.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException{

    private ErrorCode errorCode;

    public BusinessLogicException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessLogicException(String part, ErrorCode errorCode){
        super(part + " : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessLogicException(String message){
        super(message);
    }
}
