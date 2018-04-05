package com.kpi.diploma.perevertailo.service.primary;

import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;

import java.util.List;

public interface TaskService {

    List<Task> getTaskByTheme(ThemeValues themeValues, String teacherId);

    Task createTask(Task task, String teacherId);

}
