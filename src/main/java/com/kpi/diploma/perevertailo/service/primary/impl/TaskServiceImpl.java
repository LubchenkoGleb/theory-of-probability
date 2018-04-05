package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.primary.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public List<Task> getTaskByTheme(ThemeValues themeValues, String teacherId) {
        return null;
    }

    @Override
    public Task createTask(Task task, String teacherId) {
        log.info("'createTask' invoked with params'{}, {}'", task, teacherId);

        switch (task.getType()) {
            case OPEN_ANSWER:
                break;
            case SINGLE_ANSWER:
                break;
            case MULTIPLE_ANSWER:
                break;
        }

        return null;
    }

    private void processOpenAnswer() {

    }

    private void processSingleAnswer() {

    }

    private void processMultipleAnswer() {

    }

}
