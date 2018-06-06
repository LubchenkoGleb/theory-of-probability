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
public class TaskNineCalculator extends CalculatorImpl {

    public static final String NAME = "possibleNumber";
    private static final String FULL_NAME = "Формула Бернуллі";
    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;
    private static final String PARAM_N = "n";
    private static final String PARAM_P = "p";

    private static final String PARAM_M = "m";
    private static final String PARAM_PM = "pm";

    private static final String QUESTION_TEMPLATE = "Задача. Проводяться n={{" + PARAM_N + "}} випробувань, " +
            "Ймовірність настання події А в кожному з них дорівнює p={{" + PARAM_P + "}}. Знайти найбільш ймовірне" +
            "число m настання події А та його йомовірність";
    private static final String ANSWER_TEMPLATE = "m={{" + PARAM_M + "}}, P(m)={{" + PARAM_PM + "}}.";
    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + " (округлити максимум до 3го знаку)";

    public TaskNineCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.debug("'task 8' calculate'{}'", inputData);

        Integer n = Integer.valueOf(inputData.get(PARAM_N).toString());
        Double p = Double.valueOf(inputData.get(PARAM_P).toString());

        Double q = 1 - p;
        Integer m = (int) Math.ceil(n* p - q);
        double pm = MathUtil.roundDouble(MathUtil.combinations(m, n) * Math.pow(p, m) * Math.pow(q, n - m), 3);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_M, m);
        calculatedData.put(PARAM_PM, pm);
        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
