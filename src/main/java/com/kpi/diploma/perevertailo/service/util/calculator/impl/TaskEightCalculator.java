package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TaskEightCalculator extends CalculatorImpl {

    public static final String NAME = "chessPlayers";
    private static final String FULL_NAME = "Формула Бернуллі";
    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;

    private static final String PARAM_K1 = "k1";
    private static final String PARAM_N1 = "n1";
    private static final String PARAM_K2 = "k2";
    private static final String PARAM_N2 = "n2";

    private static final String PARAM_P1 = "p1";
    private static final String PARAM_P2 = "p2";



    private static final String QUESTION_TEMPLATE = "Задача. Два рівносильних шахматиста грають в шахи. Що ймовірніше:" +
            "виграти к1={{" + PARAM_K1 + "}} партій з  n1={{" + PARAM_N1 + "}} або " +
            "к1={{" + PARAM_K2 + "}} партій з  n1={{" + PARAM_N2 + "}}?";
    private static final String ANSWER_TEMPLATE = "P1={{" + PARAM_P1 +"}}, P2={{" + PARAM_P2 + "}}";
    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE;


    public TaskEightCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer k1 = Integer.valueOf(inputData.get(PARAM_K1).toString());
        Integer n1 = Integer.valueOf(inputData.get(PARAM_N1).toString());
        Integer k2 = Integer.valueOf(inputData.get(PARAM_K2).toString());
        Integer n2 = Integer.valueOf(inputData.get(PARAM_N2).toString());

        Double p1 = MathUtil.roundDouble(MathUtil.combinations(k1, n1) * Math.pow(0.5, n1), 3);
        Double p2 = MathUtil.roundDouble(MathUtil.combinations(k2, n2) * Math.pow(0.5, n2), 3);


        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_P1, p1);
        calculatedData.put(PARAM_P2, p2);

        return new CalculationData(calculatedData, "");
    }
}
