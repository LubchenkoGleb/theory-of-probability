package com.kpi.diploma.perevertailo.model.document.task;

import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.Data;

import java.util.Map;

@Data
public class OpenAnswerTask extends Task {

    private String calculations;

    private String answer;

    private Map<String, Object> calculatedValues;

    public OpenAnswerTask(String name, String fullName, String question, ThemeValues theme, TaskTypeValues type,
                          String calculations, String answer, Map<String, Object> calculatedValues) {
        super(name, fullName, question, theme, type);
        this.calculations = calculations;
        this.answer = answer;
        this.calculatedValues = calculatedValues;
    }
}
