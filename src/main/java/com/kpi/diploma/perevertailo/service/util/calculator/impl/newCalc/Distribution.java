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
public class Distribution extends CalculatorImpl {
    public static final String NAME = "Distribution";
    private static final String FULL_NAME = "Центральні моменти";
    private static final ThemeValues THEME_VALUES = ThemeValues.BASIC_LAWS_OF_DISTRIBUTION;
    private static final String PARAM_X1 = "x1";
    private static final String PARAM_P1 = "p1";
    private static final String PARAM_X2 = "x2";
    private static final String PARAM_P2 = "p2";
    private static final String PARAM_X3 = "x3";
    private static final String PARAM_P3 = "p3";

    private static final String PARAM_V1 = "v1";
    private static final String PARAM_V2 = "v2";
    private static final String PARAM_V3 = "v3";
    private static final String PARAM_V4 = "v4";
    private static final String PARAM_M2 = "m2";
    private static final String PARAM_M3 = "m3";
    private static final String PARAM_M4 = "m4";


    private static final String QUESTION_TEMPLATE = "Дискретна випадкова величина задана законом розподілу: <br>" +
            "<table><tr><th>X<sub>i</sub></th><td>{{"+ PARAM_X1 +"}}</td><td>{{"+ PARAM_X2 +"}}</td><td>{{"+ PARAM_X3 +"}}</td></tr><tr><th>P<sub>i</sub></th><td>{{"+ PARAM_P1 +"}}</td><td>{{"+ PARAM_P2 +"}}</td><td>{{"+ PARAM_P3 +"}}</td></tr></table> <br>" +
            "Знайти центральні моменти першого, другого, третього і четвертого порядків.";
    private static final String ANSWER_TEMPLATE = "Центральні моменти: <br>" +
            "μ<sub>1</sub> = 0; <br>" +
            "μ<sub>2</sub> = {{" + PARAM_M2 + "}}; <br>" +
            "μ<sub>3</sub> = {{" + PARAM_M3 + "}}; <br>" +
            "μ<sub>4</sub> = {{" + PARAM_M4 + "}}.";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE +"(округлити максимум до другого знаку)";


    public Distribution() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer x1 = Integer.valueOf(inputData.get(PARAM_X1).toString());
        Integer x2 = Integer.valueOf(inputData.get(PARAM_X2).toString());
        Integer x3 = Integer.valueOf(inputData.get(PARAM_X3).toString());
        Double p1 = Double.valueOf(inputData.get(PARAM_P1).toString());
        Double p2 = Double.valueOf(inputData.get(PARAM_P2).toString());
        Double p3 = Double.valueOf(inputData.get(PARAM_P3).toString());


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
