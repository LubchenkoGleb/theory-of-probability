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
public class MostPropableNumber extends CalculatorImpl {
    public static final String NAME = "MostPropableNumber";
    private static final String FULL_NAME = "Найімовірніше число";
    private static final ThemeValues THEME_VALUES = ThemeValues.VARIANCE_OF_RELATIVE_FREQUENCY;
    private static final String PARAM_N = "n";
    private static final String PARAM_P = "p";
    //  в одном случае в ответе К0 и К1 в другом только К0
    private static final String PARAM_K0 = "k0";


    private static final String QUESTION_TEMPLATE = "Подія може наступити = {{" + PARAM_N + "}} " +
            "разів. Ймовірність, що ця подія відбудеться = {{" + PARAM_P + "}}. Знайти найімовірніше число. <br>";
    private static final String ANSWER_TEMPLATE = "Найімовірніше число:<br>" +
            " k<sub>0</sub> = {{" + PARAM_K0 + "}}<br>";

    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE +"(округлити максимум до другого знаку)";


    public MostPropableNumber() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer n = Integer.valueOf(inputData.get(PARAM_N).toString());
        Double p = Double.valueOf(inputData.get(PARAM_P).toString());

        int res;

        double low = n * p - (1 - p);
        if (low != Math.floor(low)) {
            res = (int) Math.floor(low) + 1;
        } else if (low == Math.floor(low)) {
            res = (int) low;
        } else {
            res = (int) (n * p);
        }


        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_K0, res);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
