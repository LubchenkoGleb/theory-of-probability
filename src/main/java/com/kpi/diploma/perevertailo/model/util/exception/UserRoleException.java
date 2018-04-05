package com.kpi.diploma.perevertailo.model.util.exception;

import com.kpi.diploma.perevertailo.model.pojo.ErrorMessage;

public class UserRoleException extends BaseRuntimeException {

    public UserRoleException(String errorMessage) {
        super(new ErrorMessage(errorMessage));
    }
}
