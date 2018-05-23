package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TaskOneCalculator extends CalculatorImpl {

//    private static final String NAME = "simple_percent";
//
//    private static final String FULL_NAME = "task one calculator";
//
//    private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;
//
//    private static final String PARAM_INVESTED_MONEY = "invested_money";
//    private static final String PARAM_FINISH_SUM = "finish_sum";
//    private static final String PARAM_EACH_YEAR_PERCENTAGE = "each_year_percentage";
//    private static final String QUESTION_TEMPLATE = "how many years should we wait if we invested {{" +
//            PARAM_INVESTED_MONEY + "}} to get {{" + PARAM_FINISH_SUM +
//            "}}, if each year percent is {{" + PARAM_EACH_YEAR_PERCENTAGE + "}}";
//
//    private static final String PARAM_AMOUNT_OF_YEARS = "amount_of_years";
//    private static final String ANSWER_TEMPLATE = "amount of years : {{" + PARAM_AMOUNT_OF_YEARS + "}}";
//
//
//    public TaskOneCalculator() {
//        super(NAME, FULL_NAME, QUESTION_TEMPLATE, ANSWER_TEMPLATE, THEME_VALUES);
//    }
//
//    @Override
//    public CalculationData calculate(Map<String, Object> inputData) {
//        log.info("'calculate' invoked with params'{}'", inputData);
//
//        Double investedMoney = (Double) inputData.get(PARAM_INVESTED_MONEY);
//        Double finishSum = (Double) inputData.get(PARAM_FINISH_SUM);
//        Double eachYearPercentage = (Double) inputData.get(PARAM_EACH_YEAR_PERCENTAGE);
//
//        StringBuilder calculation = new StringBuilder();
//        int yearCounter = 0;
//        while (investedMoney <= finishSum) {
//            investedMoney *= 1 + eachYearPercentage;
//            yearCounter++;
//            calculation.append("amount of money after ").append(yearCounter).append(" = ").append(investedMoney).append("<br>");
//        }
//        log.info("calculation={}'", calculation.toString());
//
//        HashMap<String, Object> calculatedData = new HashMap<>();
//        calculatedData.put(PARAM_AMOUNT_OF_YEARS, yearCounter);
//
//        CalculationData calculationData = new CalculationData(calculatedData, calculation.toString());
//        log.info("'calculationData={}'", calculatedData);
//
//        return calculationData;
            private static final String NAME = "Перестановки";
            private static final String FULL_NAME = "ФОРМУЛА ЧИСЛА ПЕРЕСТАНОВОК";
            private static final ThemeValues THEME_VALUES = ThemeValues.DEFINITION_PROBABILITIES;
            private static final String PARAM_N = "n";
            private static final String PARAM_PN = "pN";
            private static final String QUESTION_TEMPLATE = "Щоб розрахувати число перестановок введіть число n = {{" + PARAM_N + "}}";
            private static final String ANSWER_TEMPLATE = "P<sub>n</sub> =  {{" + PARAM_PN + "}}";
            public TaskOneCalculator() {
             super(NAME, FULL_NAME, QUESTION_TEMPLATE, ANSWER_TEMPLATE, THEME_VALUES);
            }
    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

         Integer n = (Integer) inputData.get(PARAM_N);

         StringBuilder calculation = new StringBuilder();
         int pN = 1;
         while(n >= 1) {
               pN *= n;
               n--;
               calculation.append("P<sub>n</sub>").append(n).append(" = ").append(pN).append("<br>");

         }

        log.info("calculation={}'", calculation.toString());

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_PN, pN);

        CalculationData calculationData = new CalculationData(calculatedData, calculation.toString());
        log.info("'calculationData={}'", calculatedData);

        return calculationData;
    }
}
