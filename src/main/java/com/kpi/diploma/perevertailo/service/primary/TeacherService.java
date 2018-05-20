package com.kpi.diploma.perevertailo.service.primary;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Journal;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;

import java.util.List;

public interface TeacherService {

    List<Group> getListOfGroup(String teacherId);

    Task createTask(String theme, String question, String answer, String teacherId);

    List<Task> getTasks(String teacherId);

    Test createTest(Test test, String teacherId);

    Test editTest(Test test, String teacherId);

    Test deleteTest(Test test, String teacherId);

    List<Test> getTestByTheme(String theme, String teacherId);

    void assignTestToGroup(String testId, String groupId, String teacherId);

    Journal getJournal(String groupId, String teacherId);

    List<Teacher> getAll();
}
