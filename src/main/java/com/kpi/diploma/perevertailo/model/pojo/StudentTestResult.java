package com.kpi.diploma.perevertailo.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentTestResult {

    private String studentName;

    private List<Double> results;
}
