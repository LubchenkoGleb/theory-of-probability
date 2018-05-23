package com.kpi.diploma.perevertailo.model.util.exception;

import com.kpi.diploma.perevertailo.model.pojo.ErrorMessage;

public class ResourseDoesntBelongToUserException extends BaseRuntimeException {

    public ResourseDoesntBelongToUserException(String message) {
        super(new ErrorMessage(message));
    }
}
