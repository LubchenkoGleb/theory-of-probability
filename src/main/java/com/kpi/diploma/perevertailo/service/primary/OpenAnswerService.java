package com.kpi.diploma.perevertailo.service.primary;

import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;

import java.util.List;
import java.util.Map;

public interface OpenAnswerService {

    List<Task> getCalculatorsByTheme(ThemeValues themeValue);

    Task calculate(String taskName, Map<String, Object> inputParams, String teacherId);

    void checkAnswers();
}
