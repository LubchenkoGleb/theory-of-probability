package com.kpi.diploma.perevertailo.model.util.exception;

import com.kpi.diploma.perevertailo.model.pojo.ErrorMessage;

public class EmailException extends BaseRuntimeException {


    public EmailException(String message) {
        super(new ErrorMessage(message));
    }
}
