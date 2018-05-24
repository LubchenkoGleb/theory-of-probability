package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.document.task.TestResult;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.pojo.Journal;
import com.kpi.diploma.perevertailo.model.pojo.StudentTestResult;
import com.kpi.diploma.perevertailo.repository.GroupRepository;
import com.kpi.diploma.perevertailo.repository.TeacherRepository;
import com.kpi.diploma.perevertailo.service.primary.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Teacher> getAll() {

        return teacherRepository.findAllByEnable(true);
    }

    @Override
    public Journal getJournal(String groupId, String teacherId) {
        log.info("'getJournal' params'{}'", teacherId);

        Teacher teacher = teacherRepository.findById(teacherId).get();
        Group group = groupRepository.findById(groupId).get();

        List<Test> tests = teacher.getTests();
        Map<String, Test> testIdTestMap = tests.stream().collect(Collectors.toMap(Test::getId, t -> t));

        List<StudentTestResult> studentTestResults = new ArrayList<>();

        group.getStudents().forEach(st -> {

            Map<String, TestResult> testResultMap = st.getTestResults()
                    .stream().collect(Collectors.toMap(TestResult::getTestId, tr -> tr));

            List<Double> marks = new ArrayList<>();

            tests.forEach(groupTest -> {

                if (testResultMap.containsKey(groupTest.getId())) {
                    marks.add(testResultMap.get(groupTest.getId()).getResult());
                } else {
                    marks.add(0d);
                }
            });

            StudentTestResult studentTestResult =
                    new StudentTestResult(st.getFirstName() + " " + st.getLastName(), marks);

            studentTestResults.add(studentTestResult);
        });

        List<String> testNames = tests.stream().map(Test::getName).collect(Collectors.toList());
        Journal journal = new Journal(testNames, studentTestResults);
        log.info("'journal={}'", journal);

        return journal;
    }
}
