package com.kpi.diploma.perevertailo.service.util.calculator.impl.variance;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.math.LaplaceTable;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class Variance extends CalculatorImpl {
    public static final String NAME = "Variance";
    private static final String FULL_NAME = "Відхилення";
    private static final ThemeValues THEME_VALUES = ThemeValues.VARIANCE_OF_RELATIVE_FREQUENCY;
    private static final String PARAM_N = "n";
    private static final String PARAM_P = "p";

    private static final String PARAM_E = "e";
    private static final String PARAM_F = "f";
    private static final String PARAM_P1 = "p1";


    private static final String QUESTION_TEMPLATE = "Подія може наступити = {{" + PARAM_N + "}} " +
            "разів. Ймовірність, що ця подія відбудеться = {{" + PARAM_P + "}}. Знайти ймовірність, що відносна частота появи подїї" +
            "відхилиться від її ймовірності по абсолютній велечині не більш ніж на ε = {{" + PARAM_E + "}}<br>";
    private static final String ANSWER_TEMPLATE = "Ймовірність дорівнює {{" + PARAM_P1 + "}}<br>";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до 4 знаку)";


    public Variance() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = Integer.valueOf(inputData.get(PARAM_N).toString());
        Double p = Double.valueOf(inputData.get(PARAM_P).toString());
        Double e = Double.valueOf(inputData.get(PARAM_E).toString());

        double x = e * Math.sqrt(n / (p * (1 - p)));
        log.info("x={}", x);

        double res = MathUtil.roundDouble(2 * LaplaceTable.getNeares(x), 4);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_P1, res);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
