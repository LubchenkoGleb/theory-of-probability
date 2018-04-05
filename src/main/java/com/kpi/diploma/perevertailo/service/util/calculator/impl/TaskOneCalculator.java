package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TaskOneCalculator extends CalculatorImpl {

    private static final String NAME = "simple_percent";

    private static final String FULL_NAME = "task one calculator";

    private static final String PARAM_INVESTED_MONEY = "{{param_money}}";
    private static final String PARAM_FINISH_SUM = "{{param_finish_sum}}";
    private static final String PARAM_EACH_YEAR_PERCENTAGE = "{{param_each_year_percentage}}";
    private static final String QUESTION_TEMPLATE = "how many years should we wait if we invested " +
            PARAM_INVESTED_MONEY + " to get " + PARAM_FINISH_SUM +
            ", if each year percent is " + PARAM_EACH_YEAR_PERCENTAGE;

    private static final String PARAM_AMOUNT_OF_YEARS = "{{param_amount_of_years}}";
    private static final String ANSWER_TEMPLATE = "amount of years : " + PARAM_AMOUNT_OF_YEARS;

    private static final ThemeValues THEME_VALUES = ThemeValues.ONE;

    public TaskOneCalculator() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> task) {

//        Map<String, Object> inputParams = task.getInputParams();

        return null;
    }
}
