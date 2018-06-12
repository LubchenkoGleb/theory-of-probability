package com.kpi.diploma.perevertailo.service.util.calculator.impl.newCalc;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@Service

public class Batler extends CalculatorImpl {
    public static final String NAME = "Batler";
    private static final String FULL_NAME = "Статистичні гіпотези";
    private static final ThemeValues THEME_VALUES = ThemeValues.STATISTICAL_CHECK_OF_STATISTICAL_HYPOTHESIS;

    private static final String PARAM_N1 = "n1", PARAM_N2 = "n2", PARAM_N3 = "n3";
    private static final String PARAM_S1 = "s1", PARAM_S2 = "s2", PARAM_S3 = "s3";

    private static final String PARAM_K1 = "k1", PARAM_K2 = "k2", PARAM_K3 = "k3";
    private static final String PARAM_S_AVG = "savg";
    private static final String PARAM_V = "v";
    private static final String PARAM_C = "c";
    private static final String PARAM_F_NABL = "f";

//    private static final String PARAM_FKR = "fkr";  это всегда 6


    private static final String QUESTION_TEMPLATE = "По трьом незалежним вибіркам об'єми яких {{" + PARAM_N1 + "}}, {{" + PARAM_N2 + "}} та {{" + PARAM_N3 + "}},<br>" +
            "з вибірковими дисперсіями  {{" + PARAM_S1 + "}}, {{" + PARAM_S2 + "}},  {{" + PARAM_S3 + "}} <br> " +
            "При рівні значущості а= 0.05, перевірити нульову гіпотезу про однорідність дисперсій. ";

    private static final String ANSWER_TEMPLATE = "  Числа ступенів свободи: <br>" +
            "k<sub>1</sub> = {{" + PARAM_K1 + "}}; <br> " +
            "k<sub>2</sub> = {{" + PARAM_K2 + "}}; <br> " +
            "k<sub>3</sub> = {{" + PARAM_K3 + "}}; <br> " +
            "Середнє арефметичне виправлених дисперсій: <br>" +
            "s<sub>сер.</sub> = {{" + PARAM_S_AVG + "}}; <br>" +
            "F<sub>крит.</sub>(0.05, 2) = 6 <br> " +
            "V = {{" + PARAM_V + "}} <br> " +
            "C = {{" + PARAM_C + "}} <br>" +
            "F<sub>набл.</sub> = {{" + PARAM_F_NABL + "}} <br>" +
            "Якщо. Fнабл > Fкр - відкидаємо нульову гіпотезу.";


    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";


    public Batler() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n1 = Integer.valueOf(inputData.get(PARAM_N1).toString());
        Integer n2 = Integer.valueOf(inputData.get(PARAM_N2).toString());
        Integer n3 = Integer.valueOf(inputData.get(PARAM_N3).toString());
        Double s1 = Double.valueOf(inputData.get(PARAM_S1).toString());
        Double s2 = Double.valueOf(inputData.get(PARAM_S2).toString());
        Double s3 = Double.valueOf(inputData.get(PARAM_S3).toString());

        final int[] Ns = {n1, n2, n3};
        final double[] Ss = {s1, s2, s3};
        final int[] Ks = {n1 - 1, n2 - 1, n3 - 1};
        int K = Stream.of(n1, n2, n3).mapToInt(value -> value - 1).sum();

        double S;
        double tempForS = 0;
        for (int i = 0; i < 3; i++) {
            tempForS += (Ns[i] - 1) * Ss[i];
        }
        S = tempForS / K;

        double tempForV = 0;
        double tempForC = 0;
        for (int i = 0; i < 3; i++) {
            double mult = Ks[i] * Math.log10(Ss[i]);
            log.info("mult={}", mult);
            tempForV += mult;
            tempForC += 1d / Ks[i];
        }
        log.info("k={}, lgS={}, tempForV={}, tempForC={}", K, Math.log10(S), tempForV, tempForC);
        double V = 2.303 * (K * Math.log10(S) - tempForV);
        double C = 1 + (1d / 6d) * (tempForC - 1d / K);
        double fNabl = V / C;

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_K1, Ks[0]);
        calculatedData.put(PARAM_K2, Ks[1]);
        calculatedData.put(PARAM_K3, Ks[2]);
        calculatedData.put(PARAM_S_AVG, MathUtil.roundDouble(S, 3));
        calculatedData.put(PARAM_V, MathUtil.roundDouble(V, 3));
        calculatedData.put(PARAM_C, MathUtil.roundDouble(C, 3));
        calculatedData.put(PARAM_F_NABL, MathUtil.roundDouble(fNabl, 3));
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}