package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.document.task.*;
import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.dto.StudentDto;
import com.kpi.diploma.perevertailo.model.dto.TestDto;
import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import com.kpi.diploma.perevertailo.repository.*;
import com.kpi.diploma.perevertailo.service.primary.StudentService;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final TestRepository testRepository;

    private final TaskRepository taskRepository;

    private final TestTaskRepository testTaskRepository;

    private final OpenAnswerTestRepository openAnswerTestRepository;

    public StudentServiceImpl(StudentRepository studentRepository, TestRepository testRepository, TaskRepository taskRepository, TestTaskRepository testTaskRepository, OpenAnswerTestRepository openAnswerTestRepository) {
        this.studentRepository = studentRepository;
        this.testRepository = testRepository;
        this.taskRepository = taskRepository;
        this.testTaskRepository = testTaskRepository;
        this.openAnswerTestRepository = openAnswerTestRepository;
    }

    @Override
    public List<Student> getByGroup(String groupId) {
        log.info("'getListByGroup' invoked with params'{}'", groupId);

        List<Student> allByGroupId = studentRepository.getAllByGroupId(groupId);
        log.info("'allByGroupId={}'", allByGroupId);

        return allByGroupId;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        log.info("'getAll' invoked");

        List<Student> all = studentRepository.findAll();

        List<StudentDto> allDtos = all
                .stream()
                .map(student -> new StudentDto(student, student.getGroup()))
                .collect(Collectors.toList());

        return allDtos;
    }

    @Override
    public List<Student> getStudentsWithoutGroup() {

        return studentRepository.findAllByGroupAndEnable(null, true);
    }

    @Override
    public PassedTestResponse passTest(String studentId, String testId, List<Answer> answers) {
        log.info("'passTest' params '{}, {}, {}'", studentId, testId, answers);

        Student student = studentRepository.findById(studentId).get();
        Test test = testRepository.findById(testId).get();
        log.debug("'test={}'", test);

        List<Task> testTasks = test.getTask();
        log.debug("'testTasks={}'", testTasks);

        Integer questionsTasksAmount = testTasks.size();
        List<Boolean> answersCounter = new ArrayList<>();
        PassedTestResponse passedTestResponse = new PassedTestResponse();
        Map<String, Answer> taskIdAnswer = answers.stream().collect(Collectors.toMap(Answer::getTaskId, a -> a));
        log.debug("'taskIdAnswer={}'", taskIdAnswer);

        testTasks.forEach(t -> {
            log.debug("'t={}'", t);

            if (t.getType().equals(TaskTypeValues.OPEN_ANSWER)) {

                OpenAnswerTask task = openAnswerTestRepository.findById(t.getId()).get();
                Map<String, Object> correctAnswers = task.getCalculatedValues();
                log.debug("'correctAnswers={}'", correctAnswers);
                Map<String, Object> studentAnswers = taskIdAnswer.get(task.getId()).getOpenAnswerValues();
                log.debug("'studentAnswers={}'", studentAnswers);

                if (correctAnswers.keySet().equals(studentAnswers.keySet()) &&
                        correctAnswers.values().stream().map(Object::toString).collect(Collectors.toList())
                                .equals(studentAnswers.values().stream().map(Object::toString).collect(Collectors.toList()))) {
                    answersCounter.add(true);
                } else {
                    passedTestResponse.getFailedQuestions().add(task.getQuestionToStudent());
                }

            } else if (t.getType().equals(TaskTypeValues.SINGLE_ANSWER) ||
                    t.getType().equals(TaskTypeValues.OPEN_ANSWER)) {

                TestTask task = testTaskRepository.findById(t.getId()).get();
                List<AnswerOption> correctAnswers = task.getAnswerOptions();
                log.debug("'correctAnswers={}'", correctAnswers);

                List<AnswerOption> studentAnswers = taskIdAnswer.get(task.getId()).getOpenAnswerValues().entrySet()
                        .stream()
                        .map(e -> new AnswerOption(Boolean.valueOf(e.getValue().toString()), e.getKey()))
                        .collect(Collectors.toList());
                log.debug("'studentAnswers={}'", studentAnswers);

                if (correctAnswers.equals(studentAnswers)) {
                    answersCounter.add(true);
                } else {
                    passedTestResponse.getFailedQuestions().add(task.getName());
                }

            }
        });
        log.debug("'answersCounter.size()={}'", answersCounter.size());
        double mark = MathUtil.roundDouble((double) answersCounter.size() / questionsTasksAmount * 100, 2);

        student.getTestResults().add(new TestResult(mark, testId, test.getName()));
        studentRepository.save(student);

        passedTestResponse.setResultPercent(mark);

        return passedTestResponse;
    }

    @Override
    public List<Test> getNotPassedTests(String studentId) {
        Student student = studentRepository.findById(studentId).get();

        List<String> passedTestIds = student.getTestResults()
                .stream().map(TestResult::getTestId).collect(Collectors.toList());

        List<Test> notPassedTest = student.getGroup().getTests()
                .stream().filter(t -> !passedTestIds.contains(t.getId())).collect(Collectors.toList());
        log.info("'notPassedTest={}'", notPassedTest);

        return notPassedTest;
    }

    @Override
    public List<TestResult> getPassedTests(String studentId) {
        return studentRepository.findById(studentId).get().getTestResults();
    }

    @Override
    public TestDto getTestWitQuestions(String testId) {
        Test test = testRepository.findById(testId).get();
        return new TestDto(test, null, null, test.getTask());
    }


}
