package com.kpi.diploma.perevertailo.service.util.calculator.impl.newCalc;


import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.math.KohrenTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service

public class Kohren extends CalculatorImpl {
    public static final String NAME = "Kohren";
    private static final String FULL_NAME = "Статистичні гіпотези Кочрен.";
    private static final ThemeValues THEME_VALUES = ThemeValues.STATISTICAL_CHECK_OF_STATISTICAL_HYPOTHESIS;

    private static final String PARAM_N = "n";
    private static final String PARAM_S1 = "s1", PARAM_S2 = "s2", PARAM_S3 = "s3", PARAM_S4 = "s4";

    private static final String PARAM_K = "k";
    private static final String PARAM_G_NABL = "g";
    private static final String PARAM_GKR = "gkr";


    private static final String QUESTION_TEMPLATE = "По чотирьом незалежним вибіркам об'єми яких {{" + PARAM_N + "}},<br>" +
            "з вибірковими дисперсіями  {{" + PARAM_S1 + "}}, {{" + PARAM_S2 + "}},  {{" + PARAM_S3 + "}}, {{" + PARAM_S4 + "}} <br> " +
            "При рівні значущості а= 0.05, перевірити нульову гіпотезу про однорідність дисперсій. ";

    private static final String ANSWER_TEMPLATE = "  Число ступенів свободи: <br>" +
            "k = {{" + PARAM_K + "}}; <br> " +
            "G<sub>крит.</sub>(0.05, {{" + PARAM_K + "}}) = {{" + PARAM_GKR + "}}  <br> : <br>" +
            "G<sub>набл.</sub> = {{" + PARAM_G_NABL + "}} <br>" +
            "Якщо Gнабл > Gкр - відкидаємо нульову гіпотезу.";


    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";


    public Kohren() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = Integer.valueOf(inputData.get(PARAM_N).toString());
        Double d1 = Double.valueOf(inputData.get(PARAM_S1).toString());
        Double d2 = Double.valueOf(inputData.get(PARAM_S2).toString());
        Double d3 = Double.valueOf(inputData.get(PARAM_S3).toString());
        Double d4 = Double.valueOf(inputData.get(PARAM_S4).toString());
        ArrayList<Double> disps = new ArrayList<>(Arrays.asList(d1, d2, d3, d4));

        double gKr = KohrenTable.getValues(disps.size(), n - 1);
        double gNab = disps.stream().mapToDouble(value -> value).max().getAsDouble() /
                disps.stream().mapToDouble(value -> value).sum();

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_K, n - 1);
        calculatedData.put(PARAM_GKR, gKr);
        calculatedData.put(PARAM_G_NABL, gNab);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
