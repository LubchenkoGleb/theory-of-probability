//package com.kpi.diploma.perevertailo.service.util.calculator.impl.needImplement;
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
//public class SelectiveMethod extends CalculatorImpl {
//    public static final String NAME = "SelectiveMethod";
//    private static final String FULL_NAME = "Вибірковий метод";
//    private static final ThemeValues THEME_VALUES = ThemeValues.SELECTIVE_METHOD;
//
//    private static final String PARAM_X1 = "x1";
//    private static final String PARAM_X2 = "x2";
//    private static final String PARAM_X3 = "x3";
//    private static final String PARAM_X4 = "x4";
//    private static final String PARAM_X5 = "x5";
//
//    private static final String PARAM_Y2 = "y2";
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
//    private static final String QUESTION_TEMPLATE = "Дана вибірка кількості товарів проданої в магазині за 5 днів. Потрібно: <br>" +
////            "а) составить таблицу, устанавливающую зависимость между значениями указанной случайной величины и ее частотами; \n" +
////            "б) построить статистическое распределение и изобразить полигон распределения; \n" +
////            "в) найти эмпирическую функцию распределения и изобразить ее график; \n" +
////            "г) найти выборочное среднее, дисперсию, среднее квадратическое отклонение.\n";
//
//    private static final String ANSWER_TEMPLATE = "Функція розподілу : <br>" +
//            "F(x ≤{{"+ PARAM_X1+"}}) = 0; <br>" +
//            "F({{"+ PARAM_X1+"}}< x ≤{{"+ PARAM_X2+"}}) = {{" + PARAM_FUNC + "}}; <br>" +
//            "Математичне сподівання: <br>" +
//            "M(X) = {{" + PARAM_M + "}}<br>" +
//            "Дисперсія: <br>" +
//            "D(X) = {{" + PARAM_D + "}} <br>" +
//            "Середньоквадратичне відхилення  <br>" +
//            " σ(x)  = {{" + PARAM_SIGMA + "}} <br>" +
//            "Ймовірність, що випадкова величина Х набуде модливого значення в заданому проміжку: <br>" +
//            "P(a≤x≤b) = {{"+ PARAM_AB +"}}. ";
//
//    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";
//
//
//    public SelectiveMethod() {
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
