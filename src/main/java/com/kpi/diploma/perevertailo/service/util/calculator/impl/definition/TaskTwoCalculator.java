package com.kpi.diploma.perevertailo.service.util.calculator.impl.definition;

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
public class TaskTwoCalculator extends CalculatorImpl {

    public static final String NAME = "Розміщення";
    private static final String FULL_NAME = "Формула числа розміщень";
    private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;
    private static final String PARAM_N = "n";
    private static final String PARAM_K = "k";
    private static final String PARAM_A = "a";

    private static final String QUESTION_TEMPLATE = "Знайти число розміщень з n = {{" +
            PARAM_N + "}} по k = {{" + PARAM_K + "}}";
    private static final String ANSWER_TEMPLATE = "A<sub>n</sub><sup>k</sup> = {{" + PARAM_A + "}}";
    private static final String QUESTION_TO_STUDENT = "Чому дорівнює A<sub>{{"+ PARAM_N +"}}</sub><sup>{{"+ PARAM_K +"}}</sup>?";

    public TaskTwoCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = Integer.valueOf(inputData.get(PARAM_N).toString());
        Integer k = Integer.valueOf(inputData.get(PARAM_K).toString());

        StringBuilder calculation = new StringBuilder();

        Long res = MathUtil.factorial(n) / MathUtil.factorial(n - k);
        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_A, res);

        CalculationData calculationData = new CalculationData(calculatedData, calculation.toString());
        log.info("'calculationData={}'", calculatedData);
        return calculationData;
    }
}



