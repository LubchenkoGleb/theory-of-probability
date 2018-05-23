package com.kpi.diploma.perevertailo.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CalculationData {

    private Map<String, Object> calculatedValues;

    private String calculations;
}
