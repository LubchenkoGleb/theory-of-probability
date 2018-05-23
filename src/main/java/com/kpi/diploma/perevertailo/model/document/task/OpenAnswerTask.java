package com.kpi.diploma.perevertailo.model.document.task;

import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@TypeAlias("openAnswerTask")
@Document(collection = "task")
public class OpenAnswerTask extends Task {

    private String calculations;

    private String answer;

    private String questionToStudent;

    private String openAnswerTemplate;

    private Map<String, Object> calculatedValues;

    public OpenAnswerTask(String name, String question, ThemeValues theme, TaskTypeValues type,
                          String calculations, String answer, Map<String, Object> calculatedValues,
                          String openAnswerTemplate, String questionToStudent) {
        super(name, question, theme, type);
        this.calculations = calculations;
        this.answer = answer;
        this.calculatedValues = calculatedValues;
        this.openAnswerTemplate = openAnswerTemplate;
        this.questionToStudent = questionToStudent;
    }
}
