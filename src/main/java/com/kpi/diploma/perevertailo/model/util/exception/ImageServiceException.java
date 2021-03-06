package com.kpi.diploma.perevertailo.model.util.exception;

import com.kpi.diploma.perevertailo.model.pojo.ErrorMessage;
import lombok.Data;

@Data
public class ImageServiceException extends BaseRuntimeException {

    public ImageServiceException(String message) {
        super(new ErrorMessage(message));
    }
}
