package com.kpi.diploma.perevertailo.service.util.calculator.impl.newCalc;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SelectiveMethod extends CalculatorImpl {
    public static final String NAME = "SelectiveMethod";
    private static final String FULL_NAME = "Вибірковий метод";
    private static final ThemeValues THEME_VALUES = ThemeValues.SELECTIVE_METHOD;

    private static final String PARAM_X1 = "x1";
    private static final String PARAM_X2 = "x2";
    private static final String PARAM_X3 = "x3";
    private static final String PARAM_X4 = "x4";
    private static final String PARAM_X5 = "x5";

    private static final String PARAM_X_AVG = "xavg";
    private static final String PARAM_VAR = "var";
    private static final String PARAM_XD = "xd";
    private static final String PARAM_SIGMA = "sigma";
    private static final String PARAM_D = "d";
    private static final String PARAM_V = "v";
    private static final String PARAM_AS = "as";

    private static final String QUESTION_TEMPLATE = "Дана вибірка кількості товарів проданої в магазині за 5 днів. Знайти: <br>" +
            "вибіркове середнє, середнє лінійне відхилення, дисперсію, середньоквадратичне відхилення, коефіцієнт варіації та коефіцієнт асиметрії. <br>" +
            "<table><tr><th>X<sub>i</sub></th><td>{{" + PARAM_X1 + "}}</td><td>{{" + PARAM_X2 + "}}</td><td>{{" + PARAM_X3 + "}}</td><td>{{" + PARAM_X4 + "}}</td><td>{{" + PARAM_X5 + "}}</td></tr></table>";

    private static final String ANSWER_TEMPLATE = "Вибіркова середня : <br>" +
            "x<sub>сер.</sub> = {{" + PARAM_X_AVG + "}}; <br>" +
            "Середнє лінійне відхилення: <br>" +
            "d = {{" + PARAM_XD + "}} <br>" +
            "Кожне значення ряду різниться від іншого в середньому на {{" + PARAM_VAR + "}}. <br>" +
            "Дисперсія: <br>" +
            "D(X) = {{" + PARAM_D + "}} <br>" +
            "Середньоквадратичне відхилення  <br>" +
            "σ(x)  = {{" + PARAM_SIGMA + "}} <br>" +
            "Коефіцієнт варіації: <br>" +
            "v = {{" + PARAM_V + "}} <br> " +
            "Коефіцієнт асиметрії: <br>" +
            "A<sub>s</sub> = {{" + PARAM_AS + "}} <br> ";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";


    public SelectiveMethod() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Double x1 = Double.valueOf(inputData.get(PARAM_X1).toString());
        Double x2 = Double.valueOf(inputData.get(PARAM_X2).toString());
        Double x3 = Double.valueOf(inputData.get(PARAM_X3).toString());
        Double x4 = Double.valueOf(inputData.get(PARAM_X4).toString());
        Double x5 = Double.valueOf(inputData.get(PARAM_X5).toString());


        ArrayList<Double> doubles = new ArrayList<>(Arrays.asList(x1, x2, x3, x4, x5));

        Map<Double, Integer> countMap = new HashMap<>();
        doubles.forEach(n -> {
            if (countMap.containsKey(n)) {
                countMap.put(n, countMap.get(n) + 1);
            } else {
                countMap.put(n, 1);
            }
        });
        log.info("'Map={}'", countMap);

        double avg = doubles.stream().mapToDouble(n -> n).average().getAsDouble();
        double center = countMap.keySet().stream().mapToDouble(num -> num * countMap.get(num)).sum() / doubles.size();
        double var = countMap.keySet().stream().mapToDouble(num -> Math.abs(num - center) * countMap.get(num)).sum() / doubles.size();
        double disp = countMap.keySet().stream().mapToDouble(num -> Math.pow(num - center, 2) * countMap.get(num)).sum() / doubles.size();
        double sigma = Math.sqrt(disp);
        double avgPercent = sigma / avg * 100;
        double as = (disp/doubles.size()) / Math.pow(sigma, 3);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_X_AVG, MathUtil.roundDouble(avg, 4));
        calculatedData.put(PARAM_XD, MathUtil.roundDouble(center, 4));
        calculatedData.put(PARAM_VAR, MathUtil.roundDouble(var, 4));
        calculatedData.put(PARAM_D, MathUtil.roundDouble(disp, 4));
        calculatedData.put(PARAM_SIGMA, MathUtil.roundDouble(sigma, 4));
        calculatedData.put(PARAM_V, MathUtil.roundDouble(avgPercent, 4));
        calculatedData.put(PARAM_AS, MathUtil.roundDouble(as, 4));
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
