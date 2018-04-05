package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.Calculator;
import lombok.Data;

import java.util.Map;

@Data
public class CalculatorImpl implements Calculator {

    private final String name;

    private final String fullName;

    private final String questionTemplate;

    private final String answerTemplate;

    private final ThemeValues themeValues;

    public CalculatorImpl(String name, String fullName, String questionTemplate, String answerTemplate, ThemeValues themeValue) {
        this.name = name;
        this.fullName = fullName;
        this.questionTemplate = questionTemplate;
        this.answerTemplate = answerTemplate;
        this.themeValues = themeValue;
    }

    @Override
    public CalculationData calculate(Map<String, Object> input) {
        return null;
    }

    // TODO: 05.04.18 implement ASAP
    public String changePlaceHoldersToValues(String input, Map<String, Object> params) {

        return null;
    }
}
