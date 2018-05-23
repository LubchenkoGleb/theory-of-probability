package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service

public class TaskSevenCalculator  extends CalculatorImpl {
    public static final String NAME = "Формула Бернуллі";
    private static final String FULL_NAME = "Формула Бернуллі";
    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;
    private static final String PARAM_N = "n";
    private static final String PARAM_P = "p";
    private static final String PARAM_K = "k";
    private static final String PARAM_PA = "pa";
    private static final String PARAM_C = "c";
    private static final String QUESTION_TEMPLATE = "В сім'ї n =  {{" + PARAM_N + "}} дітей." +
            "Ймовірність народження хлопчика p = {{" + PARAM_P + "}}. Знайти ймовірність, що в сім'ї буде ріно" +
            "k = {{" + PARAM_K +"}} хлопчиків."

    private static final String ANSWER_TEMPLATE = " P= {{" + PARAM_PA +"}}.";




    public TaskSevenCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = (Integer) inputData.get(PARAM_N);
        Integer p = (Integer) inputData.get(PARAM_P);
        Integer k = (Integer) inputData.get(PARAM_K);

        int c = 0;
        int pa = 0;


        return null;
    }
}
}
