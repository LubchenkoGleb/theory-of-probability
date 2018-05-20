package com.kpi.diploma.perevertailo.model.dto;

import com.kpi.diploma.perevertailo.model.document.task.AnswerOption;
import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateTaskDto {

    @NotNull
    private String theme;

    @NotNull
    private String name;

    @NotNull
    @Valid
    private List<AnswerOption> answerOptions;

    @NotNull
    private String question;

    @NotNull
    private TaskTypeValues type;
}
