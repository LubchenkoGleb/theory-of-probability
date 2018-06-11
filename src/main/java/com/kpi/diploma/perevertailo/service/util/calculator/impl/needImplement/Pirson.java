package com.kpi.diploma.perevertailo.service.util.calculator.impl.needImplement;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class Pirson extends CalculatorImpl {
    public static final String NAME = "Pirson";
    private static final String FULL_NAME = "Критерій Пірсона";
    private static final ThemeValues THEME_VALUES = ThemeValues.STATISTICAL_CHECK_OF_STATISTICAL_HYPOTHESIS;
    private static final String PARAM_N = "n";
    private static final String PARAM_N1 = "n1";
    private static final String PARAM_N2 = "n2";
    private static final String PARAM_N3 = "n3";
    private static final String PARAM_N4 = "n4";
    private static final String PARAM_N5 = "n5";

    private static final String PARAM_X1 = "x1";
    private static final String PARAM_X2 = "x2";
    private static final String PARAM_X3 = "x3";
    private static final String PARAM_X4 = "x4";
    private static final String PARAM_X5 = "x5";
    private static final String PARAM_X_AVG = "xavg";

    private static final String PARAM_SIGMA = "sigma";


    private static final String PARAM_U1 = "u1";
    private static final String PARAM_U2 = "u2";
    private static final String PARAM_U3 = "u3";
    private static final String PARAM_U4 = "u4";
    private static final String PARAM_U5 = "u5";

    private static final String PARAM_F1 = "f1";
    private static final String PARAM_F2 = "f2";
    private static final String PARAM_F3 = "f3";
    private static final String PARAM_F4 = "f4";
    private static final String PARAM_F5 = "f5";

    private static final String PARAM_N_I = "ni"; // n i ' , вот эт 85,2 как у нас
    private static final String PARAM_N11 = "n11"; // n'
    private static final String PARAM_N21 = "n21";
    private static final String PARAM_N31 = "n31";
    private static final String PARAM_N41 = "n41";
    private static final String PARAM_N51 = "n51";

    private static final String PARAM_N12 = "n12";
    private static final String PARAM_N22 = "n22";
    private static final String PARAM_N32 = "n32";
    private static final String PARAM_N42 = "n42";
    private static final String PARAM_N52 = "n52";
    private static final String PARAM_H_NABL = "hnabl";
    private static final String PARAM_H_CR = "hcr";

    private static final String QUESTION_TEMPLATE = "Використовуючи критерій Пірсона при рівні значущості 0,05 перевірити чи узгоджується гіпотеза про нормальний розподіл" +
            "генеральної сукупності Х з емпіричним розподілом вибірки :  <br>" +
            "<table>" +
            "<tr><th>X<sub>i</sub></th>" +
            "<td>{{" + PARAM_X1 + "}}</td><td>{{" + PARAM_X2 + "}}</td><td>{{" + PARAM_X3 + "}}</td><td>{{" + PARAM_X4 + "}}</td><td>{{" + PARAM_X5 + "}}</td></tr>" +
            "<tr><th>n<sub>i</sub></th>" +
            "<td>{{" + PARAM_N1 + "}}</td><td>{{" + PARAM_N2 + "}}</td><td>{{" + PARAM_N3 + "}}</td><td>{{" + PARAM_N4 + "}}</td><td>{{" + PARAM_N5 + "}}</td></tr>" +
            "</table> <br>";
    private static final String ANSWER_TEMPLATE = "Використовуючи метод добутку знайдемо вибіркову середню: <br>" +
            "x<sub>сер.</sub> = {{" + PARAM_X_AVG + "}} <br>" +
            "Та вибіркове середнє квадратичне відхилення: <br>" +
            " σ(x)  = {{" + PARAM_SIGMA + "}} <br>" +
            "n'<sub>i</sub> = {{" + PARAM_N_I + "}}φ(u<sub>i</sub>) <br>" +
            "Таблиця для розрахунків : <br>" +
            "<table><tr><th>x<sub>i</sub></th><th>u<sub>i</sub></th><th>φ(u<sub>i</sub>)</th><th>n'<sub>i</sub></th></tr>" +
            "<tr><td>{{" + PARAM_X1 + "}}</td><td>{{" + PARAM_U1 + "}}</td<td>{{" + PARAM_F1 + "}}</td><td>{{" + PARAM_N11 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_X2 + "}}</td><td>{{" + PARAM_U2 + "}}</td<td>{{" + PARAM_F2 + "}}</td><td>{{" + PARAM_N21 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_X3 + "}}</td><td>{{" + PARAM_U3 + "}}</td<td>{{" + PARAM_F3 + "}}</td><td>{{" + PARAM_N31 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_X4 + "}}</td><td>{{" + PARAM_U4 + "}}</td<td>{{" + PARAM_F4 + "}}</td><td>{{" + PARAM_N41 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_X5 + "}}</td><td>{{" + PARAM_U5 + "}}</td<td>{{" + PARAM_F5 + "}}</td><td>{{" + PARAM_N51 + "}}</td></tr></table> <br>" +
            "<table><tr><th>n<sub>i</sub></th><th>n'<sub>i</sub></th><th>(n<sub>i</sub> - n'<sub>i</sub>)<sup>2</sup>/n'<sub>i</sub></th></tr>" +
            "<tr><td>{{" + PARAM_N1 + "}}</td><td>{{" + PARAM_N11 + "}}</td<td>{{" + PARAM_N12 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_N2 + "}}</td><td>{{" + PARAM_N21 + "}}</td<td>{{" + PARAM_N22 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_N3 + "}}</td><td>{{" + PARAM_N31 + "}}</td<td>{{" + PARAM_N32 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_N4 + "}}</td><td>{{" + PARAM_N41 + "}}</td<td>{{" + PARAM_N42 + "}}</td></tr>" +
            "<tr><td>{{" + PARAM_N5 + "}}</td><td>{{" + PARAM_N51 + "}}</td<td>{{" + PARAM_N52 + "}}</td></tr></table> <br>" +
            "X<sup>2</sup><sub>набл.</sub> = {{" + PARAM_H_NABL + "}} <br>" +
            "Число ступенів свободи :<br>" +
            "k = 5 - 3 = 2 <br>" +
            "X<sup>2</sup><sub>крит.</sub> = {{" + PARAM_H_CR + "}} <br>";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";


    public Pirson() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        List<Integer> Nis = new ArrayList<>();
//        Integer x1 = Integer.valueOf(inputData.get(PARAM_X1).toString());
//        Integer x2 = Integer.valueOf(inputData.get(PARAM_X2).toString());
//        Integer x3 = Integer.valueOf(inputData.get(PARAM_X3).toString());
//        Integer x4 = Integer.valueOf(inputData.get(PARAM_X4).toString());
//        Double p1 = Double.valueOf(inputData.get(PARAM_P1).toString());
//        Double p2 = Double.valueOf(inputData.get(PARAM_P2).toString());
//        Double p3 = Double.valueOf(inputData.get(PARAM_P3).toString());
//        Double p4 = Double.valueOf(inputData.get(PARAM_P4).toString());
//        Integer notLess = Integer.valueOf(inputData.get(PARAM_NOT_LESS).toString());
//        Integer notMore = Integer.valueOf(inputData.get(PARAM_NOT_MORE).toString());
//
//        double mathExpRes = x1 * p1 + x2 * p2 + x3 * p3 + x4 * p4;
//        double disp = x1 * x1 * p1 + x2 * x2 * p2 + x3 * x3 * p3 + x4 * x4 * p4 - mathExpRes * mathExpRes;
//        double sigma = Math.sqrt(disp);
//
//        double f1 = 0;
//        double f2 = f1 + p1;
//        double f3 = f2 + p2;
//        double f4 = f3 + p3;
//        double f5 = f4 + p4;
//
//        Map<Integer, Double> probMap = new HashMap<>();
//        probMap.put(1, f1);
//        probMap.put(2, f2);
//        probMap.put(3, f3);
//        probMap.put(4, f4);
//        probMap.put(5, f5);
//
//        double probRes = probMap.get(notMore) - probMap.get(notLess);
//
        HashMap<String, Object> calculatedData = new HashMap<>();
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
