package com.kpi.diploma.perevertailo.service.util.calculator.impl.needImplement;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class Kolmagorov extends CalculatorImpl {
    public static final String NAME = "kolmagorov";
    private static final String FULL_NAME = "Критерій Колмагорова";
    private static final ThemeValues THEME_VALUES = ThemeValues.STATISTICAL_CHECK_OF_STATISTICAL_HYPOTHESIS;

    private static final String PARAM_F11 = "f11", PARAM_F12 = "f12", PARAM_F13 = "f13", PARAM_F14 = "f14", PARAM_F15 = "f15";
    private static final String PARAM_F21 = "f21", PARAM_F22 = "f22", PARAM_F23 = "f23", PARAM_F24 = "f24", PARAM_F25 = "f25";

    private static final String PARAM_F11_1 = "f111", PARAM_F12_1 = "f121", PARAM_F13_1 = "f131", PARAM_F14_1 = "f141", PARAM_F15_1 = "f151"; //это f*
    private static final String PARAM_F21_1 = "f211", PARAM_F22_1 = "f221", PARAM_F23_1 = "f231", PARAM_F24_1 = "f241", PARAM_F25_1 = "f251";
    private static final String PARAM_F11_SUM = "f11s", PARAM_F12_SUM = "f12s", PARAM_F13_SUM = "f13s", PARAM_F14_SUM = "f14s", PARAM_F15_SUM = "f15s"; //это  sum f*
    private static final String PARAM_F21_SUM = "f21s", PARAM_F22_SUM = "f22s", PARAM_F23_SUM = "f23s", PARAM_F24_SUM = "f24s", PARAM_F25_SUM = "f25s";
    private static final String PARAM_F1 = "f1", PARAM_F2 = "f2", PARAM_F3 = "f3", PARAM_F4 = "f4", PARAM_F5 = "f5"; // разность

    private static final String PARAM_L = "l";    //lamda

    private static final String QUESTION_TEMPLATE = "??? <br>" +
            "<table>" +
            "<tr><th>f<sub>1</sub></th><td>{{" + PARAM_F11 + "}}</td><td>{{" + PARAM_F12 + "}}</td><td>{{" + PARAM_F13 + "}}</td><td>{{" + PARAM_F14 + "}}</td><td>{{" + PARAM_F15 + "}}</td></tr>" +
            "<tr><th>f<sub>2</sub></th><td>{{" + PARAM_F21 + "}}</td><td>{{" + PARAM_F22 + "}}</td><td>{{" + PARAM_F23 + "}}</td><td>{{" + PARAM_F24 + "}}</td><td>{{" + PARAM_F25 + "}}</td></tr>" +
            "</table> <br>";
    private static final String ANSWER_TEMPLATE = "<table>" +
            "<tr>" +
            "   <th>f<sub>1</sub></th>" +
            "   <th>f<sub>2</sub></th>" +
            "   <th>f*<sub>1</sub></th>" +
            "   <th>f*<sub>2</sub></th></th>" +
            "   <th>Sum f*<sub>1</sub></th>" +
            "   <th>Sum f*<sub>2</sub></th>" +
            "   <th>Різниця сум</th></tr>" +
            "<tr><td>{{" + PARAM_F11 + "}}</td><td>{{" + PARAM_F21 + "}}</td><td>{{" + PARAM_F11_1 + "}}</td><td>{{" + PARAM_F21_1 + "}}</td><td>{{" + PARAM_F11_SUM + "}}</td><td>{{" + PARAM_F21_SUM + "}}</td><td>{{" + PARAM_F1 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_F12 + "}}</td><td>{{" + PARAM_F22 + "}}</td><td>{{" + PARAM_F12_1 + "}}</td><td>{{" + PARAM_F22_1 + "}}</td><td>{{" + PARAM_F12_SUM + "}}</td><td>{{" + PARAM_F22_SUM + "}}</td><td>{{" + PARAM_F2 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_F13 + "}}</td><td>{{" + PARAM_F23 + "}}</td><td>{{" + PARAM_F13_1 + "}}</td><td>{{" + PARAM_F23_1 + "}}</td><td>{{" + PARAM_F13_SUM + "}}</td><td>{{" + PARAM_F23_SUM + "}}</td><td>{{" + PARAM_F3 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_F14 + "}}</td><td>{{" + PARAM_F24 + "}}</td><td>{{" + PARAM_F14_1 + "}}</td><td>{{" + PARAM_F24_1 + "}}</td><td>{{" + PARAM_F14_SUM + "}}</td><td>{{" + PARAM_F24_SUM + "}}</td><td>{{" + PARAM_F4 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_F15 + "}}</td><td>{{" + PARAM_F25 + "}}</td><td>{{" + PARAM_F15_1 + "}}</td><td>{{" + PARAM_F25_1 + "}}</td><td>{{" + PARAM_F15_SUM + "}}</td><td>{{" + PARAM_F25_SUM + "}}</td><td>{{" + PARAM_F5 + "}}</td></tr>" +
            "</table> <br>" +
            "λ<sub>эмп.</sub> = {{" + PARAM_L + "}} <br>" +
            "λ<sub>крит.</sub> = 1.36 ";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";


    public Kolmagorov() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        List<Integer> f1 = new ArrayList<>();
        List<Integer> f2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            f1.add(Integer.valueOf(inputData.get("f1" + (i + 1)).toString()));
            f2.add(Integer.valueOf(inputData.get("f2" + (i + 1)).toString()));
        }

        Integer f1Sum = f1.stream().mapToInt(v -> v).sum();
        Integer f2Sum = f2.stream().mapToInt(v -> v).sum();

        List<Double> f11 = new ArrayList<>();
        List<Double> f11Sum = new ArrayList<>();
        List<Double> f21 = new ArrayList<>();
        List<Double> f21Sum = new ArrayList<>();

        List<Double> fDif = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            f11.add((double) f1.get(i) / f1Sum);
            f21.add((double) f2.get(i) / f2Sum);

            if (i == 0) {
                f11Sum.add(f11.get(i));
                f21Sum.add(f21.get(i));
            } else {
                f11Sum.add(f11Sum.get(i - 1) + f11.get(i));
                f21Sum.add(f21Sum.get(i - 1) + f21.get(i));
            }
            fDif.add(Math.abs(f11Sum.get(i) - f21Sum.get(i)));
        }


        double lambdaEmp = fDif.stream().mapToDouble(v -> v).max().getAsDouble() *
                Math.sqrt(((double) f1Sum * f2Sum) / (f1Sum + f2Sum));

        HashMap<String, Object> calculatedData = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            calculatedData.put("f1" + (i + 1) + "1", MathUtil.roundDouble(f11.get(i), 3));
            calculatedData.put("f2" + (i + 1) + "1", MathUtil.roundDouble(f21.get(i), 3));
            calculatedData.put("f1" + (i + 1) + "s", MathUtil.roundDouble(f11Sum.get(i), 3));
            calculatedData.put("f2" + (i + 1) + "s", MathUtil.roundDouble(f21Sum.get(i), 3));
            calculatedData.put("f" + (i + 1), MathUtil.roundDouble(fDif.get(i), 3));
        }
        calculatedData.put(PARAM_L, MathUtil.roundDouble(lambdaEmp, 3));
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}