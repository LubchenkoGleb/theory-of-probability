package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.task.AnswerOption;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.document.task.TestTask;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.dto.CreateTaskDto;
import com.kpi.diploma.perevertailo.model.util.exception.IncorrectInputDataException;
import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.repository.TaskRepository;
import com.kpi.diploma.perevertailo.repository.TeacherRepository;
import com.kpi.diploma.perevertailo.repository.ThemeRepository;
import com.kpi.diploma.perevertailo.service.primary.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final ThemeRepository themeRepository;

    private final TeacherRepository teacherRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ThemeRepository themeRepository, TeacherRepository teacherRepository) {
        this.taskRepository = taskRepository;
        this.themeRepository = themeRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Task> getTaskByTheme(ThemeValues themeValues, String teacherId) {
        log.debug("'getTaskByTheme' params'{}, {}'", themeValues, teacherId);

        List<Task> response = taskRepository.findAllByTeacherIdAndTheme(teacherId, themeValues);
        log.debug("'response={}'", response);

        return response;
    }

    @Override
    public Task createTask(CreateTaskDto createTaskDto, String teacherId) {
        log.info("'createTask' params'{}, {}'", createTaskDto, teacherId);

        if (createTaskDto.getType().equals(TaskTypeValues.OPEN_ANSWER)) {
            throw new IncorrectInputDataException("wrong answer type");
        }

        long correctAnswersAmount = createTaskDto.getAnswerOptions().stream().filter(AnswerOption::getCorrect).count();
        log.debug("'correctAnswersAmount={}'", correctAnswersAmount);

        if(createTaskDto.getType().equals(TaskTypeValues.SINGLE_ANSWER) && correctAnswersAmount != 1) {
            throw new IncorrectInputDataException("single answer question can contain only one correct answer");
        } else if (createTaskDto.getType().equals(TaskTypeValues.MULTIPLE_ANSWER) && correctAnswersAmount < 1) {
            throw new IncorrectInputDataException("multiple answer question must contain at least only one correct answer");
        }

        TestTask task = new TestTask(
                createTaskDto.getName(),
                createTaskDto.getQuestion(),
                ThemeValues.valueOf(createTaskDto.getTheme().toUpperCase()),
                createTaskDto.getType(),
                createTaskDto.getAnswerOptions());

        Teacher teacher = teacherRepository.findById(teacherId).get();
        task.setTeacher(teacher);

        TestTask saveTask = taskRepository.save(task);
        log.info("'saveTask={}'", saveTask);

        return saveTask;
    }

    @Override
    public Task deleteTask(String taskId, String teacherId) {
        log.debug("'deleteTask' params'{}, {}'", taskId, teacherId);

        Task task = taskRepository.findByIdAndTeacherId(taskId, teacherId);
        log.debug("'task for delete={}'", task);

        taskRepository.delete(task);

        return task;
    }


    private void processOpenAnswer() {

    }

    private void processSingleAnswer() {

    }

    private void processMultipleAnswer() {

    }

}
