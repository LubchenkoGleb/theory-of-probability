package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TaskTwoCalculator extends CalculatorImpl {

    public static final String NAME = "task_2";
    private static final String FULL_NAME = "task 2 calculator";
    private static final String QUESTION_TEMPLATE = "task 2 question template";
    private static final String ANSWER_TEMPLATE = "task 2 open answer template";
    private static final ThemeValues THEME_VALUES = ThemeValues.ONE;

    public TaskTwoCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> task) {

        return null;
    }
}
