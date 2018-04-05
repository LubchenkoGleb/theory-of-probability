package com.kpi.diploma.perevertailo.model.document;

import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.Data;

import java.util.Map;

@Data
public class Task {

    private String question;

    private String answerTemplate;

    private ThemeValues theme;

    private TaskTypeValues type;

    private Map<String, Object> answers;

}
