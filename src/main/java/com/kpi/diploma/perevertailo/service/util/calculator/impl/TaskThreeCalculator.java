package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TaskThreeCalculator extends CalculatorImpl {
    public static final String NAME = "Комбінації";
    private static final String FULL_NAME = "Комбінації з n елементів по k";
    private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;
    private static final String PARAM_N = "n";
    private static final String PARAM_K = "k";
    private static final String PARAM_C = "с";
    private static final String QUESTION_TEMPLATE = "Знайти число розміщень з n ={{" +
                                             PARAM_N + "}} по k = {{" + PARAM_K + "}}";
    private static final String ANSWER_TEMPLATE = "С<sub>n</sub><sup>k</sup> =  {{"+ PARAM_C + "}}";
    private static final String QUESTION_TO_STUDENT = "Чому дорівнює Pn({{" + PARAM_N + "}})";



    public TaskThreeCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> task) {

        return null;
    }
}
