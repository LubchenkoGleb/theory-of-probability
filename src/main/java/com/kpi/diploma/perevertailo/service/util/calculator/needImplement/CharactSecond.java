//package com.kpi.diploma.perevertailo.service.util.calculator.needImplement;
//
//import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
//import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
//import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
//import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Service
//public class CharactSecond extends CalculatorImpl {
//    public static final String NAME = "СontinuousСharateristicFunc";
//    private static final String FULL_NAME = "Числові характеристики неперервної випадкової величини";
//    private static final ThemeValues THEME_VALUES = ThemeValues.BASIC_NUMERICAL_CHARACTERISTICS;
//
//    private static final String PARAM_F = "func";
//    private static final String PARAM_A = "a";
//    private static final String PARAM_B = "b";
//    private static final String PARAM_X1 = "x1";
//    private static final String PARAM_X2 = "x2";
//
//    private static final String PARAM_FUNC = "funcRosp";
//    private static final String PARAM_M = "m";
//    private static final String PARAM_D = "d";
//    private static final String PARAM_SIGMA = "sigma";
//
//
//    private static final String PARAM_AB = "ab";
//
//
//    private static final String QUESTION_TEMPLATE = "Неперервна випадкова величина задана функцією розподілу: <br>" +
//            "f(x) = {{"+ PARAM_F + "}} ;<br>" +
//            "{{" + PARAM_X1 +"}} < x < {{" + PARAM_X2 +"}} <br> " +
//            "Знайти матсподівання, дисперсію, середньоквадратичне відхилення та щільність розподілу. <br> " +
//            "Знайти ймовірність того, що випадкова величина X прийме значення з проміжку [{{"+ PARAM_A +"}};{{" + PARAM_B +"}}].<br>";
//
//    private static final String ANSWER_TEMPLATE = "Щільність розподілу : <br>" +
//            "0, x ≤{{"+ PARAM_X1+"}} <br>" +
//            "{{" + PARAM_FUNC + "}},{{"+ PARAM_X1+"}}< x ≤{{"+ PARAM_X2+"}} <br>" +
//            "0, x > {{"+ PARAM_X2+"}}  <br>" +
//            "Математичне сподівання: <br>" +
//            "M(X) = {{" + PARAM_M + "}}<br>" +
//            "Дисперсія: <br>" +
//            "D(X) = {{" + PARAM_D + "}} <br>" +
//            "Середньоквадратичне відхилення  <br>" +
//            " σ(x)  = {{" + PARAM_SIGMA + "}} <br>" +
//            "Ймовірність, що випадкова величина Х набуде можливого значення в заданому проміжку: <br>" +
//            "P(a≤x≤b) = {{"+ PARAM_AB +"}}. ";
//
//    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";
//
//
//    public CharactSecond() {
//        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
//    }
//
//    @Override
//    public CalculationData calculate(Map<String, Object> inputData) {
//        log.info("'calculate' invoked with params'{}'", inputData);
//
//        Double a = Double.valueOf(inputData.get(PARAM_A).toString());
//        Double b = Double.valueOf(inputData.get(PARAM_B).toString());
//        Double x1 = Double.valueOf(inputData.get(PARAM_X1).toString());
//        Double x2 = Double.valueOf(inputData.get(PARAM_X2).toString());
//        String f = inputData.get(PARAM_F).toString();
//
//        double fRes = MathUtil.calculateIntegralOnFB(f, x1, x2);
//
////        double mathExpRes = x1 * p1 + x2 * p2 + x3 * p3 + x4 * p4;
////        double disp = x1 * x1 * p1 + x2 * x2 * p2 + x3 * x3 * p3 + x4 * x4 * p4 - mathExpRes * mathExpRes;
////        double sigma = Math.sqrt(disp);
////
////        double f1 = 0;
////        double f2 = f1 + p1;
////        double f3 = f2 + p2;
////        double f4 = f3 + p3;
////        double f5 = f4 + p4;
////
////        Map<Integer, Double> probMap = new HashMap<>();
////        probMap.put(1, f1);
////        probMap.put(2, f2);
////        probMap.put(3, f3);
////        probMap.put(4, f4);
////        probMap.put(5, f5);
//
//        double probRes = probMap.get(notMore) - probMap.get(notLess);
//
//        HashMap<String, Object> calculatedData = new HashMap<>();
//        calculatedData.put(PARAM_M, MathUtil.roundDouble(mathExpRes, 4));
//        calculatedData.put(PARAM_D, MathUtil.roundDouble(disp, 4));
//        calculatedData.put(PARAM_SIGMA, MathUtil.roundDouble(sigma, 4));
////        calculatedData.put(PARAM_F1, f1);
////        calculatedData.put(PARAM_F2, f2);
////        calculatedData.put(PARAM_F3, f3);
////        calculatedData.put(PARAM_F4, f4);
////        calculatedData.put(PARAM_F5, f5);
//        calculatedData.put(PARAM_AB, MathUtil.roundDouble(probRes, 4));
//
//        log.info("'calculatedData={}'", calculatedData);
//
//        CalculationData calculationData = new CalculationData(calculatedData, "");
//
//        return calculationData;
//    }
//}
