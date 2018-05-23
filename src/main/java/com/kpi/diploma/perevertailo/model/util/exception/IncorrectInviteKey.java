package com.kpi.diploma.perevertailo.model.util.exception;

import com.kpi.diploma.perevertailo.model.pojo.ErrorMessage;
import lombok.Data;

@Data
public class IncorrectInviteKey extends BaseRuntimeException {

    public IncorrectInviteKey(String message) {
        super(new ErrorMessage(message));
    }
}
