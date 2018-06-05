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
public class PuassonFormula extends CalculatorImpl {

    public static final String NAME = "puassonFormula";
    private static final String FULL_NAME = "Задача про стрілків";
    private static final ThemeValues THEME_VALUES = ThemeValues.DEVIATION_OF_RELATIVE_FREQUENCY;

    private static final String PARAM_N = "n";
    private static final String PARAM_P = "p";

    private static final String PARAM_EQ = "eq";
    private static final String PARAM_LESS = "less";
    private static final String PARAM_NOT_LESS = "notLess";
    private static final String PARAM_MORE = "more";
    private static final String PARAM_NOT_MORE = "notMore";
    private static final String PARAM_NOT_LESS2 = "notLess2";
    private static final String PARAM_NOT_MORE2 = "notMore2";

    private static final String PARAM_EXACTLY_ONE = "exactlyOne";
    private static final String PARAM_NOT_LESS_AND_NOT_MORE = "notLessAndNotMore";


    private static final String QUESTION_TEMPLATE = "Число випробувань: n={{" + PARAM_N + "}}, ймовірність p={{" + PARAM_P + "}}." +
            "Яка йомвірність того що подія настане:<br>" +
            "настане рівно {{" + PARAM_EQ + "}} разів<br>" +
            "менше {{" + PARAM_LESS + "}} разів<br>" +
            "не менше {{" + PARAM_NOT_LESS + "}} разів<br>" +
            "більше{{" + PARAM_MORE + "}} разів<br>" +
            "не більше{{" + PARAM_NOT_MORE + "}} разів<br>" +
            "не менше {{" + PARAM_NOT_LESS2 + "}} та не більше {{" + PARAM_NOT_MORE2 + "}} разів<br>" +
            "хоча б один раз";
    private static final String ANSWER_TEMPLATE = "йомвірність того що подія настане:<br>" +
            "настане рівно  {{" + PARAM_EQ + "}} разів<br>" +
            "менше {{" + PARAM_LESS + "}} разів<br>" +
            "не менше {{" + PARAM_NOT_LESS + "}} разів<br>" +
            "більше{{" + PARAM_MORE + "}} разів<br>" +
            "не більше{{" + PARAM_NOT_MORE + "}} разів<br>" +
            "не менше та не більше {{" + PARAM_NOT_LESS_AND_NOT_MORE + "}} разів<br>" +
            "хоча б один раз {{" + PARAM_EXACTLY_ONE + "}}";
    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE;

    public PuassonFormula() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = Integer.valueOf(inputData.get(PARAM_N).toString());
        Double p = Double.valueOf(inputData.get(PARAM_P).toString());
        Integer eq = Integer.valueOf(inputData.get(PARAM_EQ).toString());
        Integer less = Integer.valueOf(inputData.get(PARAM_LESS).toString());
        Integer notLess = Integer.valueOf(inputData.get(PARAM_NOT_LESS).toString());
        Integer more = Integer.valueOf(inputData.get(PARAM_MORE).toString());
        Integer notMore = Integer.valueOf(inputData.get(PARAM_NOT_MORE).toString());
        Integer notLess2 = Integer.valueOf(inputData.get(PARAM_NOT_LESS2).toString());
        Integer notMore2 = Integer.valueOf(inputData.get(PARAM_NOT_MORE2).toString());

        double eqRes = MathUtil.puasson(eq, n, p);
        double lessRss = MathUtil.puassonSum(0, less - 1, n, p);
        double notLessRss = MathUtil.puassonSum(notLess, n, n, p);
        double moreRes = MathUtil.puassonSum(more + 1, n, n, p);
        double notMoreRes = MathUtil.puassonSum(0, notMore, n, p);
        double notLessAndNotMoreRes = MathUtil.puassonSum(notLess2, notMore2, n, p);
        double exactlyOneRes = 1 - MathUtil.puasson(0, n, p);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_EQ, eqRes);
        calculatedData.put(PARAM_LESS, lessRss);
        calculatedData.put(PARAM_NOT_LESS, notLessRss);
        calculatedData.put(PARAM_MORE, moreRes);
        calculatedData.put(PARAM_NOT_MORE, notMoreRes);
        calculatedData.put(PARAM_NOT_LESS_AND_NOT_MORE, notLessAndNotMoreRes);
        calculatedData.put(PARAM_EXACTLY_ONE, exactlyOneRes);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
