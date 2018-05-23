package com.kpi.diploma.perevertailo.model.document.task;

import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@TypeAlias("testTask")
@Document(collection = "task")
public class TestTask extends Task {

    private List<AnswerOption> answerOptions;

    public TestTask(String name, String question,
                    ThemeValues theme, TaskTypeValues type, List<AnswerOption> anwerOptions) {
        super(name, question, theme, type);
        this.answerOptions = anwerOptions;
    }

    public TestTask() {
    }
}
