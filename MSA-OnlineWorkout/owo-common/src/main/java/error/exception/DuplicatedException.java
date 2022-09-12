package error.exception;

import error.model.ErrorCode;

public class DuplicatedException extends BusinessLogicException{
    public DuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
