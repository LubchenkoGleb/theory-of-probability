package com.kpi.diploma.perevertailo.model.pojo;

import lombok.Data;

@Data
public class ErrorMessage {
    private boolean isException = true;
    private String message;
    private String exceptionName;

    public ErrorMessage(String message) {
        this.message = message;
    }
}
