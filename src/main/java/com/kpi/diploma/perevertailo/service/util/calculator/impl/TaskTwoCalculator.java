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
public class TaskTwoCalculator extends CalculatorImpl {

    public static final String NAME = "Розміщення";
    private static final String FULL_NAME = "Формула числа розміщень";
    private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;
    private static final String PARAM_N = "n";
    private static final String PARAM_K = "k";
    private static final String PARAM_A = "a";

    private static final String QUESTION_TEMPLATE = "Знайти число розміщень з n ={{" +
            PARAM_N + "}} по k = {{" + PARAM_K + "}}";
    private static final String ANSWER_TEMPLATE = "A<sub>n</sub><sup>k</sup> = {{" + PARAM_A + "}}";
    private static final String QUESTION_TO_STUDENT = "Чому дорівнює A<sub>n</sub><sup>k</sup> ({{" + PARAM_N + "}})";

    public TaskTwoCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = (Integer) inputData.get(PARAM_N);
        Integer k = (Integer) inputData.get(PARAM_K);

        StringBuilder calculation = new StringBuilder();

        Long res = MathUtil.factorial(n) / MathUtil.factorial(n - k);
//        int t = 1;
//        int p;
//        int q = 1;
//        int a = 0;
//        p = n - k;
//
//        while(n >= 1) {
//            t *= n;
//            n--;
//            while (p >= 1) {
//                q *= p;
//                p--;
//                a = t/q;
//                calculation.append("<i>A<sub>n</sub><sup>k</sup></i>").append(n).append(" = ").append(a).append("<br>");
//            }
//        }

//        log.info("calculation={}'", calculation.toString());
//
        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_A, res);
//
        CalculationData calculationData = new CalculationData(calculatedData, calculation.toString());
//        log.info("'calculationData={}'", calculatedData);
//
        return calculationData;
    }
}



