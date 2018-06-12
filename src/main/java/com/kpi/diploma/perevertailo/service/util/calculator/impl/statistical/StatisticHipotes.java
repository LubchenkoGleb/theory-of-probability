package com.kpi.diploma.perevertailo.service.util.calculator.impl.statistical;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.math.FisherTable;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class StatisticHipotes extends CalculatorImpl {
    public static final String NAME = "StatisticHipotes";
    private static final String FULL_NAME = "Статистичні гіпотези";
    private static final ThemeValues THEME_VALUES = ThemeValues.STATISTICAL_CHECK_OF_STATISTICAL_HYPOTHESIS;

    private static final String PARAM_N1 = "n1";
    private static final String PARAM_N2 = "n2";
    private static final String PARAM_DX = "dx";
    private static final String PARAM_DY = "dy";


    private static final String PARAM_F = "f";
    private static final String PARAM_F1 = "f1";
    private static final String PARAM_F2 = "f2";
    private static final String PARAM_FKR = "fkr";


    private static final String QUESTION_TEMPLATE = "По двум незалежним вибіркам об'єми яких {{" + PARAM_N1 + "}} та {{" + PARAM_N2 + "}},<br>" +
            "з вибірковими дисперсіями D(X) = {{" + PARAM_DX + "}} та D(Y) = {{" + PARAM_DY + "}} <br> " +
            "При рівні значущості а= 0.05, перевірити нульову гіпотезу H<sub>0</sub>: D(X)=D(Y); H<sub>1</sub>: D(X) не дорівнює D(Y). ";

    private static final String ANSWER_TEMPLATE = "Значення критерію Фішера : <br>" +
            "F<sub>набл.</sub> = {{" + PARAM_F + "}}; <br>" +
            "Числа ступенів свободи: <br>" +
            "f<sub>1</sub> = {{" + PARAM_F1 + "}}; <br> " +
            "f<sub>2</sub> = {{" + PARAM_F2 + "}}; <br> " +
            "F<sub>крит.</sub> = {{" + PARAM_FKR + "}} <br> " +
            "Нульова гіпотеза приймається, якщо  Fексп. < Fкр.";


    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";


    public StatisticHipotes() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n1 = Integer.valueOf(inputData.get(PARAM_N1).toString());
        Integer n2 = Integer.valueOf(inputData.get(PARAM_N2).toString());
        Double dx = Double.valueOf(inputData.get(PARAM_DX).toString());
        Double dy = Double.valueOf(inputData.get(PARAM_DY).toString());

        double max = Math.max(dx, dy);
        double min = Math.min(dx, dy);

        double fnabl = max / min;
        double fcrit = max == dx ? FisherTable.table[n2 - 2][n1 - 2] : FisherTable.table[n1 - 2][n2 - 2];

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_F, MathUtil.roundDouble(fnabl, 3));
        calculatedData.put(PARAM_F1, n1 - 1);
        calculatedData.put(PARAM_F2, n2 - 1);
        calculatedData.put(PARAM_FKR, fcrit);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}