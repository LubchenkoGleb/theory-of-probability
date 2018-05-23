package com.kpi.diploma.perevertailo.model.util.exception;

import com.kpi.diploma.perevertailo.model.pojo.ErrorMessage;

public class ResourceNotFoundException extends BaseRuntimeException {

    public ResourceNotFoundException(String message) {
        super(new ErrorMessage(message));
    }
}
