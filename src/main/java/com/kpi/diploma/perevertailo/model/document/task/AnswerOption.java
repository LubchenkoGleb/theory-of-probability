package com.kpi.diploma.perevertailo.model.document.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AnswerOption {

    @NotNull
    private Boolean correct;

    @NotNull
    private Object answer;

    public AnswerOption() {
    }
}
