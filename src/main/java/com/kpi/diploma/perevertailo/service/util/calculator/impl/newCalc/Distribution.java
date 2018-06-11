package com.kpi.diploma.perevertailo.service.util.calculator.impl.newCalc;
import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class Distribution extends CalculatorImpl {
    public static final String NAME = "Distribution";
    private static final String FULL_NAME = "Центральні моменти";
    private static final ThemeValues THEME_VALUES = ThemeValues.RANGE_OF_DISTRIBUTION;
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
    private static final String PARAM_M1 = "m1";
    private static final String PARAM_M2 = "m2";
    private static final String PARAM_M3 = "m3";
    private static final String PARAM_M4 = "m4";


    private static final String QUESTION_TEMPLATE = "Дискретна випадкова величина задана законом розподілу: <br>" +
            "<table><tr><th>X<sub>i</sub></th><td>{{"+ PARAM_X1 +"}}</td><td>{{"+ PARAM_X2 +"}}</td><td>{{"+ PARAM_X3 +"}}</td></tr><tr><th>P<sub>i</sub></th><td>{{"+ PARAM_P1 +"}}</td><td>{{"+ PARAM_P2 +"}}</td><td>{{"+ PARAM_P3 +"}}</td></tr></table> <br>" +
            "Знайти центральні моменти першого, другого, третього і четвертого порядків.";
    private static final String ANSWER_TEMPLATE = "Центральні моменти: <br>" +
            "μ<sub>1</sub> = {{" + PARAM_M1 + "}}; <br>" +
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

        int[] x = {x1, x2, x3};
        double[] p = {p1, p2, p3};

        double m1 = MathUtil.centMoments(x, p, 1);
        double m2 = MathUtil.centMoments(x, p, 2);
        double m3 = MathUtil.centMoments(x, p, 3);
        double m4 = MathUtil.centMoments(x, p, 4);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_M1, MathUtil.roundDouble(m1, 3));
        calculatedData.put(PARAM_M2, MathUtil.roundDouble(m2, 3));
        calculatedData.put(PARAM_M3, MathUtil.roundDouble(m3, 3));
        calculatedData.put(PARAM_M4, MathUtil.roundDouble(m4, 3));
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
