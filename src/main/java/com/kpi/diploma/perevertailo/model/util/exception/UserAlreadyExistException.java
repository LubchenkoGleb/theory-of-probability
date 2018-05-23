package com.kpi.diploma.perevertailo.model.util.exception;

import com.kpi.diploma.perevertailo.model.pojo.ErrorMessage;

public class UserAlreadyExistException extends BaseRuntimeException {

    public UserAlreadyExistException(String message) {
        super(new ErrorMessage(message));
    }
}
