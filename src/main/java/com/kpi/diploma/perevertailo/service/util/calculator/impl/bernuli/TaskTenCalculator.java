package com.kpi.diploma.perevertailo.service.util.calculator.impl.bernuli;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TaskTenCalculator extends CalculatorImpl {

    public static final String NAME = "ФормулаБернуллі";
    private static final String FULL_NAME = "Формула Бернуллі";
    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;
    private static final String PARAM_N = "n";
    private static final String PARAM_P = "p";
    private static final String PARAM_K = "k";
    private static final String PARAM_PA = "pa";
    private static final String PARAM_C = "c";
    private static final String QUESTION_TEMPLATE = "В сім'ї n =  {{" + PARAM_N + "}} дітей." +
            "Ймовірність народження хлопчика p = {{" + PARAM_P + "}}. Знайти ймовірність, що в сім'ї буде ріно" +
            "k = {{" + PARAM_K +"}} хлопчиків.";
    private static final String ANSWER_TEMPLATE = " P= {{" + PARAM_PA +"}}.";
    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE +" (округлити максимум до другого знаку)";


    public TaskTenCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
//        Integer k1 = Integer.valueOf(inputData.get(PARAM_K1).toString());
//        Integer n1 = Integer.valueOf(inputData.get(PARAM_N1).toString());
//        Integer k2 = Integer.valueOf(inputData.get(PARAM_K2).toString());
//        Integer n2 = Integer.valueOf(inputData.get(PARAM_N2).toString());
//
//
//        HashMap<String, Object> calculatedData = new HashMap<>();
////        calculatedData.put(PARAM_PA, res);
//        CalculationData calculationData = new CalculationData(calculatedData, "");
//
//        return calculationData;
        return null;
    }
}
