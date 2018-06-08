package com.kpi.diploma.perevertailo.service.util.calculator.impl.definition;

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
public class TaskFourCalculator extends CalculatorImpl {
    public static final String NAME = "ЗадачаПроКулі";
    private static final String FULL_NAME = "Задача про кулі";
    private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;

    private static final String PARAM_N = "n"; // всего шариков K + M
    private static final String PARAM_CCK = "ck"; //первая комбинация для расчета с C по K
    private static final String PARAM_CDM = "cdm"; //вторая комбинация с d по m
    private static final String PARAM_CAN = "can"; //третья комбинация с a по n


    private static final String PARAM_K = "k"; //белых шаиков
    private static final String PARAM_M = "m"; //черных шариков
    private static final String PARAM_A = "a"; // достали без возвращения шаров C + D
    private static final String PARAM_C = "с"; // количество белых шаров которое достали
    private static final String PARAM_D = "d"; // количество черных шаров которое достали
    private static final String PARAM_P = "p"; // искомая вероятность


    private static final String QUESTION_TEMPLATE = "В урні знаходяться K = {{" + PARAM_K + "}} – білих та M = {{" + PARAM_M + "}}- чорних куль." +
            " З неї навмання і не повертаючи виймають {{" + PARAM_A + "}} куль. Знайти ймовірність того, що буде " +
            "обрано рівно {{" + PARAM_C + "}} білих та {{" + PARAM_D + "}} чорних куль.";
    private static final String ANSWER_TEMPLATE = "P = {{" + PARAM_P + "}}";
    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(відповідь округлити до 4х знаків після коми)";


    public TaskFourCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer c = Integer.valueOf(inputData.get(PARAM_C).toString());
        Integer k = Integer.valueOf(inputData.get(PARAM_K).toString());
        Integer m = Integer.valueOf(inputData.get(PARAM_M).toString());
        Integer a = Integer.valueOf(inputData.get(PARAM_A).toString());
        Integer d = Integer.valueOf(inputData.get(PARAM_D).toString());
        int n = k + m;

        long cck = MathUtil.combinations(c, k);
        log.info("cck={}", cck);
        long cdm = MathUtil.combinations(d, m);
        log.info("cdm={}", cdm);
        long can = MathUtil.combinations(a, n);
        log.info("can={}", can);

        double res =  (double) cck * cdm / can;

        res = MathUtil.roundDouble(res, 4);
//        int ck = 0;
//        int cdm = 0;
//        int can = 0;

        String calculations = "";
//                "C<sup>" + c + "</sup><sub>" + k + "</sub> = " + ck + "<br>" +
//                "C<sup>" + d + "</sup><sub>" + m + "</sub> = " + cdm + "<br>" +
//                "C<sup>" + a + "</sup><sub>" + n + "</sub> = " + can + "";

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_P, res);

        CalculationData calculationData = new CalculationData(calculatedData, calculations);

        return calculationData;
    }

}


