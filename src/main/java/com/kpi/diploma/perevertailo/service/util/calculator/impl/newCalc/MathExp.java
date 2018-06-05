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
public class MathExp extends CalculatorImpl {
    public static final String NAME = "Characteristics";
    private static final String FULL_NAME = "Числові характеристики дискретної випадкової величини";
    private static final ThemeValues THEME_VALUES = ThemeValues.RANGE_OF_DISTRIBUTION;
    private static final String PARAM_X1 = "x1";
    private static final String PARAM_P1 = "p1";
    private static final String PARAM_X2 = "x2";
    private static final String PARAM_P2 = "p2";
    private static final String PARAM_X3 = "x3";
    private static final String PARAM_P3 = "p3";
    private static final String PARAM_X4 = "x4";
    private static final String PARAM_P4 = "p4";
    private static final String PARAM_NOT_LESS = "notLess";
    private static final String PARAM_NOT_MORE = "notMore";

    private static final String PARAM_F1 = "f1";
    private static final String PARAM_F2 = "f2";
    private static final String PARAM_F3 = "f3";
    private static final String PARAM_F4 = "f4";
    private static final String PARAM_F5 = "f5";
    private static final String PARAM_M = "m";
    private static final String PARAM_D = "d";
    private static final String PARAM_SIGMA = "sigma";
    private static final String PARAM_NOT_LESS_AND_NOT_MORE = "notLessAndNotMore";


    private static final String QUESTION_TEMPLATE = "Дискретна випадкова величина задана законом розподілу: <br>" +
            "<table><tr><th>X<sub>i</sub></th><td>{{"+ PARAM_X1 +"}}</td><td>{{"+ PARAM_X2 +"}}</td><td>{{"+ PARAM_X3 +"}}</td><td>{{"+ PARAM_X4 +"}}</td></tr><tr><th>P<sub>i</sub></th><td>{{"+ PARAM_P1 +"}}</td><td>{{"+ PARAM_P2 +"}}</td><td>{{"+ PARAM_P3 +"}}</td><td>{{"+ PARAM_P4 +"}}</td></tr></table> <br>" +
            "Ймовірність, що випадкова величина Х набуде можливого значення в проміжику: <br>" +
            "не менше ніж {{" + PARAM_NOT_LESS + "}} і не більше ніж {{" + PARAM_NOT_MORE + "}} <br>" +
            "Знайти матсподівання, дисперсію, середньоквадратичне відхилення та функцію розподілу.";
    private static final String ANSWER_TEMPLATE = "Математичне сподівання: <br>" +
            "M(X) = {{" + PARAM_M + "}}<br>" +
            "Дисперсія: <br>" +
            "D(X) = {{" + PARAM_D + "}} <br>" +
            "Середньоквадратичне відхилення  <br>" +
            " σ(x)  = {{" + PARAM_SIGMA + "}} <br>" +
            "Функція розподілу : <br>" +
            "F(x≤x<sub>1</sub>) = {{" + PARAM_F1 +"}}; <br>" +
            "F(x<sub>1</sub>< x ≤x<sub>2</sub>) = {{" + PARAM_F2 +"}}; <br>" +
            "F(x<sub>2</sub>< x ≤x<sub>3</sub>) = {{" + PARAM_F3 +"}}; <br>" +
            "F(x<sub>3</sub>< x ≤x<sub>4</sub>) = {{" + PARAM_F4 +"}}; <br>" +
            "F(x > x<sub>4</sub>) = {{" + PARAM_F5 +"}}. <br>" +
            "Ймовірність, що випадкова величина Х набуде модливого значення в заданому проміжку: <br>" +
            "P(a≤x<b) = {{"+ PARAM_NOT_LESS_AND_NOT_MORE +"}}. <br> ";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE +"(округлити максимум до другого знаку)";


    public MathExp() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer x1 = Integer.valueOf(inputData.get(PARAM_X1).toString());
        Integer x2 = Integer.valueOf(inputData.get(PARAM_X2).toString());
        Integer x3 = Integer.valueOf(inputData.get(PARAM_X3).toString());
        Integer x4 = Integer.valueOf(inputData.get(PARAM_X4).toString());
        Double p1 = Double.valueOf(inputData.get(PARAM_P1).toString());
        Double p2 = Double.valueOf(inputData.get(PARAM_P2).toString());
        Double p3 = Double.valueOf(inputData.get(PARAM_P3).toString());
        Double p4 = Double.valueOf(inputData.get(PARAM_P4).toString());
        Integer notLess = Integer.valueOf(inputData.get(PARAM_NOT_LESS).toString());
        Integer notMore = Integer.valueOf(inputData.get(PARAM_NOT_MORE).toString());

//        double eqRes = MathUtil.puasson(eq, n, p);
//        double lessRss = MathUtil.puassonSum(0, less - 1, n, p);
//        double notLessRss = MathUtil.puassonSum(notLess, n, n, p);
//        double moreRes = MathUtil.puassonSum(more + 1, n, n, p);
//        double notMoreRes = MathUtil.puassonSum(0, notMore, n, p);
//        double notLessAndNotMoreRes = MathUtil.puassonSum(notLess2, notMore2, n, p);
//        double exactlyOneRes = 1 - MathUtil.puasson(0, n, p);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_M, m);
        calculatedData.put(PARAM_D, d);
        calculatedData.put(PARAM_SIGMA, sigma);

//        calculatedData.put(PARAM_MORE, moreRes);
//        calculatedData.put(PARAM_NOT_MORE, notMoreRes);
//        calculatedData.put(PARAM_NOT_LESS_AND_NOT_MORE, notLessAndNotMoreRes);
//        calculatedData.put(PARAM_EXACTLY_ONE, exactlyOneRes);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
