package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Journal;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.repository.GroupRepository;
import com.kpi.diploma.perevertailo.repository.TeacherRepository;
import com.kpi.diploma.perevertailo.service.primary.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final GroupRepository groupRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, GroupRepository groupRepository) {
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getListOfGroup(String teacherId) {
        return groupRepository.findAllByTeacherId(teacherId);
    }

    @Override
    public Task createTask(String theme, String question, String answer, String teacherId) {
        return null;
    }

    @Override
    public List<Task> getTasks(String teacherId) {
        return null;
    }

    @Override
    public Test createTest(Test test, String teacherId) {
        return null;
    }

    @Override
    public Test editTest(Test test, String teacherId) {
        return null;
    }

    @Override
    public Test deleteTest(Test test, String teacherId) {
        return null;
    }

    @Override
    public List<Test> getTestByTheme(String theme, String teacherId) {
        return null;
    }

    @Override
    public void assignTestToGroup(String testId, String groupId, String teacherId) {

    }

    @Override
    public Journal getJournal(String groupId, String teacherId) {
        return null;
    }

    @Override
    public List<Teacher> getAll() {

        return teacherRepository.findAllByEnable(true);
    }
}
