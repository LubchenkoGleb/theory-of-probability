package com.kpi.diploma.perevertailo.service.util.calculator;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;

import java.util.Map;

public interface Calculator {

    CalculationData calculate(Map<String, Object> input);
}
