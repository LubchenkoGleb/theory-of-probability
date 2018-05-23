package com.kpi.diploma.perevertailo.model.document.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestResult {

    private Double result;

    private String testId;

    private String testName;

    public TestResult() {
    }
}
