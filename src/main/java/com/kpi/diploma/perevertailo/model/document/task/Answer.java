package com.kpi.diploma.perevertailo.model.document.task;

import lombok.Data;

import java.util.Map;

@Data
public class Answer {

    private String taskId;

    private Map<String, Object> openAnswerValues;
}
