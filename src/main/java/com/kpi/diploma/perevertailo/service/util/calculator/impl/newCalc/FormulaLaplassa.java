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
public class FormulaLaplassa extends CalculatorImpl {
    public static final String NAME = "LaplassFormula";
    private static final String FULL_NAME = "Формула Лапласса";
    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;
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


    private static final String QUESTION_TEMPLATE = "Подія може наступи = {{" + PARAM_N + "}}" +
            "разів. Ймовірність, що ця подія відбудеться =  {{" + PARAM_P + "}}. Знайти ймоаврність, що подія: <br>" +
            "1) наступить {{" + PARAM_EQ + "}} разів; <br>" +
            "2) менше {{" + PARAM_LESS + "}} разів; <br>" +
            "3) не менш ніж {{" + PARAM_NOT_LESS + "}} разів; <br>" +
            "4) більше ніж {{" + PARAM_MORE + "}} разів; <br>" +
            "5) не більше ніж {{" + PARAM_NOT_MORE + "}} разів; <br> " +
            "6) не менше ніж {{" + PARAM_NOT_LESS2 + "}} і не більше ніж {{" + PARAM_NOT_MORE2 + "}} разів; <br>" +
            "7) хоча б один раз.";
    private static final String ANSWER_TEMPLATE = "йомвірність того що подія настане задану кількість разів:<br>" +
            "1) P = {{" + PARAM_EQ + "}}<br>" +
            "2) P = {{" + PARAM_LESS + "}} <br>" +
            "3) P = {{" + PARAM_NOT_LESS + "}} <br>" +
            "4) P = {{" + PARAM_MORE + "}} <br>" +
            "5) P = {{" + PARAM_NOT_MORE + "}} <br>" +
            "6) P = {{" + PARAM_NOT_LESS_AND_NOT_MORE + "}} <br>" +
            "7) P = {{" + PARAM_EXACTLY_ONE + "}}";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + " (округлити максимум до другого знаку)";


    public FormulaLaplassa() {
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

        double eqRes = MathUtil.roundDouble(MathUtil.calcLaplaseForOneValue(n, p, eq, MathUtil.calcXFroLaplase(eq, n, p)), 4);
        double lessRes = MathUtil.roundDouble(MathUtil.calcLaplase(0, less - 1, n, p), 4);
        double moreRes = MathUtil.roundDouble(MathUtil.calcLaplase(more + 1, n, n, p), 4);
        double notLessRes = MathUtil.roundDouble(MathUtil.calcLaplase(notLess, n, n, p), 4);
        double notMoreRes = MathUtil.roundDouble(MathUtil.calcLaplase(notMore, 0, n, p), 4);
        double notLessAndNotMoreRes = MathUtil.roundDouble(MathUtil.calcLaplase(notLess2, notMore2, n, p), 4);
        double exactlyOneRes = MathUtil.roundDouble(1 - Math.pow(1 - p, n), 4);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_EQ, eqRes);
        calculatedData.put(PARAM_LESS, lessRes);
        calculatedData.put(PARAM_NOT_LESS, notLessRes);
        calculatedData.put(PARAM_MORE, moreRes);
        calculatedData.put(PARAM_NOT_MORE, notMoreRes);
        calculatedData.put(PARAM_NOT_LESS_AND_NOT_MORE, notLessAndNotMoreRes);
        calculatedData.put(PARAM_EXACTLY_ONE, exactlyOneRes);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
