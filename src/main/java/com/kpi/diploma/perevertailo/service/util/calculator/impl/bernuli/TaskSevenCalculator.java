package com.kpi.diploma.perevertailo.service.util.calculator.impl.bernuli;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service

public class TaskSevenCalculator  extends CalculatorImpl {
    public static final String NAME = "Bernuli";
    private static final String FULL_NAME = "Формула Бернуллі";
    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;
    private static final String PARAM_N = "n";
    private static final String PARAM_P = "p";
    private static final String PARAM_K = "k";
    private static final String PARAM_PA = "pa";
    private static final String PARAM_C = "c";
    private static final String QUESTION_TEMPLATE = "В сім'ї n =  {{" + PARAM_N + "}} дітей." +
            "Ймовірність народження хлопчика p = {{" + PARAM_P + "}}. Знайти ймовірність, що в сім'ї буде рівно " +
            "k = {{" + PARAM_K +"}} хлопчиків.";
    private static final String ANSWER_TEMPLATE = "P = {{" + PARAM_PA +"}}.";
    private static final String QUESTION_TO_STUDENT = "В сім'ї n =  {{" + PARAM_N + "}} дітей. Ймовірність народження хлопчика p = {{" + PARAM_P + "}}. Знайти ймовірність, що в сім'ї буде рівно k = {{" + PARAM_K +"}} хлопчиків. (округлити максимум до другого знаку)";


    public TaskSevenCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = Integer.valueOf(inputData.get(PARAM_N).toString());
        Double p = Double.valueOf(inputData.get(PARAM_P).toString());
        Integer k = Integer.valueOf(inputData.get(PARAM_K).toString());

        double res = MathUtil.combinations(k, n) * Math.pow(p, k) * Math.pow(1-p, n - k);
        res = MathUtil.roundDouble(res, 3);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_PA, res);
        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}

