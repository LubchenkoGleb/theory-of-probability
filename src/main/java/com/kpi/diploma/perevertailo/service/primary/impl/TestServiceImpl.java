package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.dto.CreateTestDto;
import com.kpi.diploma.perevertailo.model.dto.TestDto;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.repository.*;
import com.kpi.diploma.perevertailo.service.primary.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    private final GroupRepository groupRepository;

    private final TestRepository testRepository;

    private final TeacherRepository teacherRepository;

    private final ThemeRepository themeRepository;

    private final TaskRepository taskRepository;

    public TestServiceImpl(GroupRepository groupRepository, TestRepository testRepository,
                           TeacherRepository teacherRepository, ThemeRepository themeRepository,
                           TaskRepository taskRepository) {
        this.groupRepository = groupRepository;
        this.testRepository = testRepository;
        this.teacherRepository = teacherRepository;
        this.themeRepository = themeRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TestDto> getAllByGroup(String groupId) {
        Group group = groupRepository.findById(groupId).get();

        List<TestDto> response = group.getTests().stream()
                .map(t -> new TestDto(t, t.getTeacher(), t.getGroups(), t.getTask()))
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public List<TestDto> getNotAssignedToGroup(String groupId) {
        Group group = groupRepository.findById(groupId).get();

        List<String> excludeIds = group.getTests().stream()
                .map(Test::getId).collect(Collectors.toList());

        List<TestDto> response = testRepository.findAllByIdNotIn(excludeIds).stream()
                .map(t -> new TestDto(t, t.getTeacher(), t.getGroups(), t.getTask()))
                .collect(Collectors.toList());
        log.debug("'response={}'", response);

        return response;
    }

    @Override
    public Test deleteFromGroup(String groupId, String testId) {
        Group group = groupRepository.findById(groupId).get();
        Test testForRemove = null;
        for (Test t : group.getTests()) {
            if (t.getId().equals(testId)) {
                testForRemove = t;
                break;
            }
        }
        group.getTests().remove(testForRemove);
        groupRepository.save(group);

        Group groupForRemove = null;
        Test test = testRepository.findById(testId).get();
        for (Group g : test.getGroups()) {
            if (g.getId().equals(groupId)) {
                groupForRemove = g;
            }
        }
        test.getGroups().remove(groupForRemove);
        return testRepository.save(test);
    }

    @Override
    public Test addToGroup(String groupId, String testId) {
        Group group = groupRepository.findById(groupId).get();
        Test test = testRepository.findById(testId).get();

        group.getTests().add(test);
        groupRepository.save(group);

        test.getGroups().add(group);
        return testRepository.save(test);
    }

    @Override
    public Test createTest(CreateTestDto createTestDto, String teacherId) {
        Test test = new Test();

        Teacher teacher = teacherRepository.findById(teacherId).get();
        test.setTeacher(teacher);

        List<Task> tasks = taskRepository.findAllByIdIn(createTestDto.getTaskIds());
        test.setTask(tasks);

        test.setTheme(createTestDto.getTheme());
        test.setName(createTestDto.getName());

        Test saveTest = testRepository.save(test);

        teacher.getTests().add(test);
        teacherRepository.save(teacher);

        return saveTest;
    }
}
