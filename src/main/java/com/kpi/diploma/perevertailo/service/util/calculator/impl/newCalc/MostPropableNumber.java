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
    private static final String FULL_NAME = "Найймовірніше число";
    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;
    private static final String PARAM_N = "n";
    private static final String PARAM_P = "p";
    //  в одном случае в ответе К0 и К1 в другом только К0
    private static final String PARAM_K0 = "k0";
    private static final String PARAM_K1 = "k1";


    private static final String QUESTION_TEMPLATE = "Подія може наступи = {{" + PARAM_N + "}}" +
            "разів. Ймовірність, що ця подія відбудеться =  {{" + PARAM_P + "}}. Знайти найймовірніше число. <br>";
    private static final String ANSWER_TEMPLATE = "Найймовірніше число для данного випадку:<br>" +
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
        Integer k0 = Integer.valueOf(inputData.get(PARAM_K0).toString());
        Integer k1 = Integer.valueOf(inputData.get(PARAM_K1).toString());

//        double eqRes = MathUtil.puasson(eq, n, p);
//        double lessRss = MathUtil.puassonSum(0, less - 1, n, p);
//        double notLessRss = MathUtil.puassonSum(notLess, n, n, p);
//        double moreRes = MathUtil.puassonSum(more + 1, n, n, p);
//        double notMoreRes = MathUtil.puassonSum(0, notMore, n, p);
//        double notLessAndNotMoreRes = MathUtil.puassonSum(notLess2, notMore2, n, p);
//        double exactlyOneRes = 1 - MathUtil.puasson(0, n, p);

        HashMap<String, Object> calculatedData = new HashMap<>();
//        calculatedData.put(PARAM_EQ, eqRes);
//        calculatedData.put(PARAM_LESS, lessRss);
//        calculatedData.put(PARAM_NOT_LESS, notLessRss);
//        calculatedData.put(PARAM_MORE, moreRes);
//        calculatedData.put(PARAM_NOT_MORE, notMoreRes);
//        calculatedData.put(PARAM_NOT_LESS_AND_NOT_MORE, notLessAndNotMoreRes);
//        calculatedData.put(PARAM_EXACTLY_ONE, exactlyOneRes);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}
