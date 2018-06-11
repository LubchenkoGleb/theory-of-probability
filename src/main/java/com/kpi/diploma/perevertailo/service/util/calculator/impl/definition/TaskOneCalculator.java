package com.kpi.diploma.perevertailo.service.util.calculator.impl.definition;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TaskOneCalculator extends CalculatorImpl {

    private static final String NAME = "Permutations";
    private static final String FULL_NAME = "Перестановки";
    private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;
    private static final String PARAM_N = "n";
    private static final String PARAM_PN = "pN";

    private static final String QUESTION_TEMPLATE = "Введіть число перестановок для n = {{" + PARAM_N + "}}";
    private static final String ANSWER_TEMPLATE = "Pn =  {{" + PARAM_PN + "}}";
    private static final String QUESTION_TO_STUDENT_TEMPLATE = " Знайти число перестановок P<sub>{{" + PARAM_N + "}}</sub> ?";


    public TaskOneCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT_TEMPLATE, ANSWER_TEMPLATE, THEME_VALUES);
    }


    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = Integer.valueOf(inputData.get(PARAM_N).toString());

         StringBuilder calculation = new StringBuilder();
         int pN = 1;
         while(n >= 1) {
               pN *= n;
               n--;
               calculation.append("P<sub>n</sub>").append(n).append(" = ").append(pN).append("<br>");

        }

        log.info("calculation={}'", calculation.toString());

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_PN, pN);

        CalculationData calculationData = new CalculationData(calculatedData, calculation.toString());
        log.info("'calculationData={}'", calculatedData);

        return calculationData;
    }
}
