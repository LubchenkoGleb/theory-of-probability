package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TaskFourCalculator extends CalculatorImpl {
    public static final String NAME = "Задача про кулі";
    private static final String FULL_NAME = "Задача про кулі";
    private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;
    private static final String PARAM_N = "n"; // всего шариков K + M
    private static final String PARAM_K = "k"; //белых шаиков
    private static final String PARAM_M = "m"; //черных шариков
    private static final String PARAM_A = "a"; // достали без возвращения шаров C + D
    private static final String PARAM_C = "с"; // количество белых шаров которое достали
    private static final String PARAM_D = "d"; // количество черных шаров которое достали
    private static final String PARAM_P = "p"; // искомая вероятность
    private static final String PARAM_CCK = "ck"; //первая комбинация для расчета с C по K
    private static final String PARAM_CDM = "cdm"; //вторая комбинация с d по m
    private static final String PARAM_CAN = "can"; //третья комбинация с a по n
    private static final String QUESTION_TEMPLATE = "В урні знаходяться K = {{" + PARAM_K + "}} – білих та M = {{" + PARAM_M + "}}- чорних куль." +
            " З неї навмання і не повертаючи виймають {{" + PARAM_A + "}} куль. Знайти ймовірність того, що буде " +
            "обрано рівно {{" + PARAM_C + "}} білих та {{" + PARAM_D + "}} чорних куль.";
    private static final String ANSWER_TEMPLATE = "P = {{" + PARAM_P + "}}";
    private static final String QUESTION_TO_STUDENT = "";


    public TaskFourCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer c = (Integer) inputData.get(PARAM_C);
        Integer k = (Integer) inputData.get(PARAM_K);
        Integer m = (Integer) inputData.get(PARAM_M);
        Integer a = (Integer) inputData.get(PARAM_A);
        Integer d = (Integer) inputData.get(PARAM_D);
        int n = k + m;
        int ck = 0;
        int cdm = 0;
        int can = 0;


        String calculations = "C<sup>" + c + "</sup><sub>" + k + "</sub> = " + ck + "<br>" +
                "C<sup>" + d + "</sup><sub>" + m + "</sub> = " + cdm + "<br>" +
                "C<sup>" + a + "</sup><sub>" + n + "</sub> = " + can + "";
        return null;
    }

}


