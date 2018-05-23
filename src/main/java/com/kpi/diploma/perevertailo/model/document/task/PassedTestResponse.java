package com.kpi.diploma.perevertailo.model.document.task;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PassedTestResponse {

    private double resultPercent;

    private List<String> failedQuestions = new ArrayList<>();
}
