package com.kpi.diploma.perevertailo.service.util.calculator.impl.basicNumerical;

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
public class CharactFirst extends CalculatorImpl {
    public static final String NAME = "СontinuousСharateristic";
    private static final String FULL_NAME = "Числові характеристики неперервної випадкової величини";
    private static final ThemeValues THEME_VALUES = ThemeValues.BASIC_NUMERICAL_CHARACTERISTICS;

    private static final String PARAM_F = "func";
    private static final String PARAM_A = "a";
    private static final String PARAM_B = "b";
    private static final String PARAM_X1 = "x1";
    private static final String PARAM_X2 = "x2";

    private static final String PARAM_FUNC = "funcRosp";
    private static final String PARAM_M = "m";
    private static final String PARAM_D = "d";
    private static final String PARAM_SIGMA = "sigma";


    private static final String PARAM_AB = "ab";


    private static final String QUESTION_TEMPLATE = "Неперервна випадкова величина задана щільністю розподілу: <br>" +
            "f(x) = {{" + PARAM_F + "}} ;<br>" +
            "{{" + PARAM_X1 + "}} < x < {{" + PARAM_X2 + "}} <br> " +
            "Знайти математичне сподівання, дисперсію, середньоквадратичне відхилення та побудувати функцію розподілу. <br> " +
            "Знайти ймовірність того, що випадкова величина X прийме значення з проміжку [{{" + PARAM_A + "}};{{" + PARAM_B + "}}].<br>";

    private static final String ANSWER_TEMPLATE = "Функція розподілу : <br>" +
            "F(x ≤{{" + PARAM_X1 + "}}) = 0; <br>" +
            "F({{" + PARAM_X1 + "}}< x ≤{{" + PARAM_X2 + "}}) = {{" + PARAM_FUNC + "}}; <br>" +
            "Математичне сподівання: <br>" +
            "M(X) = {{" + PARAM_M + "}}<br>" +
            "Дисперсія: <br>" +
            "D(X) = {{" + PARAM_D + "}} <br>" +
            "Середньоквадратичне відхилення  <br>" +
            "σ(x)  = {{" + PARAM_SIGMA + "}} <br>" +
            "Ймовірність, що випадкова величина Х набуде можливого значення в заданому проміжку: <br>" +
            "P(a≤x≤b) = {{" + PARAM_AB + "}}. ";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";


    public CharactFirst() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Double a = Double.valueOf(inputData.get(PARAM_A).toString());
        Double b = Double.valueOf(inputData.get(PARAM_B).toString());
        Double x1 = Double.valueOf(inputData.get(PARAM_X1).toString());
        Double x2 = Double.valueOf(inputData.get(PARAM_X2).toString());
        String f = inputData.get(PARAM_F).toString();

        double fRes = MathUtil.calculateIntegralOnFB(f, x1, x2);
        double mRes = MathUtil.calculateIntegralOnFB("x*(" + f + ")", x1, x2);
        double dRes = MathUtil.calculateIntegralOnFB("x^2*(" + f + ")", x1, x2) - mRes*mRes;
        double sigma = Math.sqrt(Math.abs(dRes));
        double resAb = MathUtil.calculateIntegralOnFB(f, a, b);

        Map<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_FUNC, MathUtil.roundDouble(fRes, 3));
        calculatedData.put(PARAM_M, MathUtil.roundDouble(mRes, 3));
        calculatedData.put(PARAM_D, MathUtil.roundDouble(dRes, 3));
        calculatedData.put(PARAM_SIGMA, MathUtil.roundDouble(sigma, 3));
        calculatedData.put(PARAM_AB, MathUtil.roundDouble(resAb, 3));
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
