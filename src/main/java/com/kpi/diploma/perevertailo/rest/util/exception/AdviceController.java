package com.kpi.diploma.perevertailo.rest.util.exception;

import com.kpi.diploma.perevertailo.model.pojo.ErrorMessage;
import com.kpi.diploma.perevertailo.model.util.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ErrorMessage> handleBaseRuntimeExceptions(BaseRuntimeException ex) {
        log.error("'handleBaseRuntimeExceptions' invoked with params'{}'", ex.getErrorMessage().getMessage());

        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
            stringBuilder.append(stackTraceElement.toString());
        }
        log.error(stringBuilder.toString());

        ErrorMessage errorMessage = ex.getErrorMessage();
        errorMessage.setExceptionName(ex.getClass().getSimpleName());
        log.error("'errorMessage={}'", errorMessage);

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Order
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOtherExceptions(HttpServletRequest request, Exception ex) {
        log.error("'handleOtherExceptions' invoked for request'{}'", request.getRequestURL());

        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
            stringBuilder.append(stackTraceElement.toString());
        }
        log.error(stringBuilder.toString());

        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        errorMessage.setExceptionName(ex.getClass().getSimpleName());
        log.error("'errorMessage={}'", errorMessage);

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
