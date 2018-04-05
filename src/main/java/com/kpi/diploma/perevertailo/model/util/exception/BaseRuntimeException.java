package com.kpi.diploma.perevertailo.model.util.exception;

import com.kpi.diploma.perevertailo.model.pojo.ErrorMessage;
import lombok.Data;


@Data
public class BaseRuntimeException extends RuntimeException {
    private ErrorMessage errorMessage;

    public BaseRuntimeException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
