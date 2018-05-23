package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Map;

@Slf4j
@Service

public class TaskFourCalculator extends CalculatorImpl {
    public static final String NAME = "task_4";
    private static final String FULL_NAME = "Задача про кулі";
    private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;
    private static final String PARAM_N = "n"; // всего шариков K + M
    private static final String PARAM_K = "k"; //белых шаиков
    private static final String PARAM_M = "m"; //черных шариков
    private static final String PARAM_A = "a"; // достали без возвращения шаров C + D
    private static final String PARAM_C = "с"; // количество белых шаров которое достали
    private static final String PARAM_D = "d"; // количество черных шаров которое достали
    private static final String PARAM_P = "p"; // искомая вероятность
    private static final String QUESTION_TEMPLATE = "В урні знаходяться K = {{" + PARAM_K + "}} – білих та M = {{" + PARAM_M + "}}- чорних куль."+
            " З неї навмання і не повертаючи виймають {{"+ PARAM_A + "}} куль. Знайти ймовірність того, що буде " +
            "обрано рівно {{"+ PARAM_C  + "}} білих та {{"+ PARAM_D +"}} чорних куль.";
    private static final String ANSWER_TEMPLATE = "P = {{" + PARAM_P + "}}";



    public TaskFourCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> task) {
        String calculations = "C<sup>";
        return null;
    }

}


