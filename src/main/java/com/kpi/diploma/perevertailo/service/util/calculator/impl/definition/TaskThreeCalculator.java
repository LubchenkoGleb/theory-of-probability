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
public class TaskThreeCalculator extends CalculatorImpl {
    public static final String NAME = "Комбінації";
    private static final String FULL_NAME = "Комбінації з n елементів по k";
    private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;
    private static final String PARAM_N = "n";
    private static final String PARAM_K = "k";
    private static final String PARAM_C = "c";
    private static final String QUESTION_TEMPLATE = "Знайти число розміщень з n ={{" +
                                             PARAM_N + "}} по k = {{" + PARAM_K + "}}";
    private static final String ANSWER_TEMPLATE = "С<sub>n</sub><sup>k</sup> =  {{"+ PARAM_C + "}}";
    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE;



    public TaskThreeCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> task) {
        log.info("'calculate taskThree' params'{}'", task);

        Integer n = Integer.valueOf(task.get(PARAM_N).toString());
        Integer k = Integer.valueOf(task.get(PARAM_K).toString());

        long res = MathUtil.combinations(k, n);
        log.info("'res={}'", res);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_C, res);
//
        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
