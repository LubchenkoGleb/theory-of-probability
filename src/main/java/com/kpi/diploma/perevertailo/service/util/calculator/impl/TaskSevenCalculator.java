//package com.kpi.diploma.perevertailo.service.util.calculator.impl;
//
//import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
//import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Slf4j
//@Service
//
//public class TaskSevenCalculator  extends CalculatorImpl {
//    public static final String NAME = "Формула Бернуллі";
//    private static final String FULL_NAME = "Формула Бернуллі";
//    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;
//    private static final String PARAM_N = "n";
//    private static final String PARAM_P = "p";
//    private static final String PARAM_PAHONE = "paho";
//    private static final String PARAM_PAHTWO = "paht";
//    private static final String PARAM_PA = "pa";
//    private static final String PARAM_PHAONE = "pao";
//    private static final String PARAM_PHATWO = "pat";
//    private static final String QUESTION_TEMPLATE = "На заводі випускають деталі двох типів, {{" + PARAM_PHONE + "}}" +
//            "- кількість деталей першого типу, {{" + PARAM_PHTWO + "}} - кількість деталей другого типу. Ймовірність, що навмання" +
//            "взята деталь першого типу буде стандартна P(A|H<sub>1</sub>) = {{"+ PARAM_PAHONE +"}}, другого типу буде стандартна P(A|H<sub>2</sub>) = {{" + PARAM_PAHTWO +"}}." +
//            "Знайти ймовірність, що 1)взята навмання деталь буде стандартною першого типу; <br>" +
//            "2)взята навмання деталь буде стандартною другого типу. ";
//    private static final String ANSWER_TEMPLATE = "Ймовірність того, що деталь виявиться стандартною P(A)= {{" + PARAM_PA +"}}.<br>" +
//            "Ймовірність того, що взята деталь була першого типу P(H<sub>1</sub>|A) = {{"+ PARAM_PHAONE +"}}.<br>" +
//            "Ймовірність того, що взята деталь була другого типу P(H<sub>2</sub>|A) = {{"+ PARAM_PHATWO +"}}.";
//
//
//
//    public TaskSevenCalculator() {
//        super(NAME, FULL_NAME, QUESTION_TEMPLATE, ANSWER_TEMPLATE, THEME_VALUES);
//    }
//
//    @Override
//    public CalculationData calculate(Map<String, Object> inputData) {
//        log.info("'calculate' invoked with params'{}'", inputData);
//
//        Integer pho = (Integer) inputData.get(PARAM_PHONE);
//        Integer pht = (Integer) inputData.get(PARAM_PHTWO);
//        Integer paho = (Integer) inputData.get(PARAM_PAHONE);
//        Integer paht = (Integer) inputData.get(PARAM_PAHTWO);
//
//        int ph1 = 0;
//        int ph2 = 0;
//        int pa = 0;
//        int pao = 0;
//        int pat = 0;
//
//        String calculations = "P(H<sub>1</sub> = " + ph1 +"<br>" +
//                "P(H<sub>2</sub> = " + ph2 +"<br>" +
//                "P(A)=P(A|H1)P(H1) + P(A|H2)P(H2)="+ paho + "*" + ph1 +" + "+ paht + "*" + ph2 +".";
//
//        return null;
//    }
//}
//}
