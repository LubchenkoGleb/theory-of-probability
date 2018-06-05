package com.kpi.diploma.perevertailo.service.util.calculator.impl.newCalc;
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
public class Variance extends CalculatorImpl {
    public static final String NAME = "Variance";
    private static final String FULL_NAME = "Відхилення";
    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;
    private static final String PARAM_N = "n";
    private static final String PARAM_P = "p";

    private static final String PARAM_E = "e";
    private static final String PARAM_F = "f";
    private static final String PARAM_P1 = "p1";


    private static final String QUESTION_TEMPLATE = "Подія може наступи = {{" + PARAM_N + "}}" +
            "разів. Ймовірність, що ця подія відбудеться =  {{" + PARAM_P + "}}. Знайти ймовірність, що відносна частота появи подїї" +
            "відхилиться від її ймовірності по абсолютній велечині не більш ніж на ε = {{" + PARAM_E + "}}<br>";
    private static final String ANSWER_TEMPLATE = "Ймовірність дорівнює {{" + PARAM_P1 + "}}<br>";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE +"(округлити максимум до другого знаку)";


    public Variance() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = Integer.valueOf(inputData.get(PARAM_N).toString());
        Double p = Double.valueOf(inputData.get(PARAM_P).toString());
        Integer e = Integer.valueOf(inputData.get(PARAM_E).toString());

//        double eqRes = MathUtil.puasson(eq, n, p);
//        double lessRss = MathUtil.puassonSum(0, less - 1, n, p);
//        double notLessRss = MathUtil.puassonSum(notLess, n, n, p);
//        double moreRes = MathUtil.puassonSum(more + 1, n, n, p);
//        double notMoreRes = MathUtil.puassonSum(0, notMore, n, p);
//        double notLessAndNotMoreRes = MathUtil.puassonSum(notLess2, notMore2, n, p);
//        double exactlyOneRes = 1 - MathUtil.puasson(0, n, p);

        HashMap<String, Object> calculatedData = new HashMap<>();
//        calculatedData.put(PARAM_EQ, eqRes);
//        calculatedData.put(PARAM_LESS, lessRss);
//        calculatedData.put(PARAM_NOT_LESS, notLessRss);
//        calculatedData.put(PARAM_MORE, moreRes);
//        calculatedData.put(PARAM_NOT_MORE, notMoreRes);
//        calculatedData.put(PARAM_NOT_LESS_AND_NOT_MORE, notLessAndNotMoreRes);
//        calculatedData.put(PARAM_EXACTLY_ONE, exactlyOneRes);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
