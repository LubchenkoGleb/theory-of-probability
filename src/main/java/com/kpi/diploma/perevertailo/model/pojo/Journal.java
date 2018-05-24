package com.kpi.diploma.perevertailo.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Journal {

    private List<String> testName;

    private List<StudentTestResult> results;
}
