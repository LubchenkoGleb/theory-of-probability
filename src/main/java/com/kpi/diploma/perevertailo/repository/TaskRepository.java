package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findAllByIdIn(List<String> ids);

    List<Task> findAllByTeacherIdAndTheme(String teacherId, ThemeValues themeValues);

    Task findByIdAndTeacherId(String taskId, String teacherId);
}
