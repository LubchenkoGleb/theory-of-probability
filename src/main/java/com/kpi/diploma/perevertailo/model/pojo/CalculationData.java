package com.kpi.diploma.perevertailo.model.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class CalculationData {

    private Map<String, Object> calculatedValues;

    private String calculations;

}
