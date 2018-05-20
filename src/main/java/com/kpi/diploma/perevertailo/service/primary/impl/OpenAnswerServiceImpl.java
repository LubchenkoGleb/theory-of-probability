package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.task.OpenAnswerTask;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.exception.ResourceNotFoundException;
import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.repository.TaskRepository;
import com.kpi.diploma.perevertailo.repository.UserRepository;
import com.kpi.diploma.perevertailo.service.primary.OpenAnswerService;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.TaskOneCalculator;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.TaskThreeCalculator;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.TaskTwoCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class OpenAnswerServiceImpl implements OpenAnswerService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private Map<String, Task> taskTemplatesByName;

    private Map<ThemeValues, List<Task>> tasksByTheme;

    private Map<String, CalculatorImpl> calculatorsByName;


    public OpenAnswerServiceImpl(TaskOneCalculator taskOneCalculator,
                                 TaskTwoCalculator taskTwoCalculator,
                                 TaskThreeCalculator taskThreeCalculator,
                                 UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;

        List<CalculatorImpl> calculators = Arrays.asList(taskOneCalculator, taskTwoCalculator, taskThreeCalculator);

        this.taskTemplatesByName = new HashMap<>();
        this.tasksByTheme = new HashMap<>();
        for (ThemeValues themeValues : ThemeValues.values()) {
            this.tasksByTheme.put(themeValues, new ArrayList<>());
        }

        this.calculatorsByName = new HashMap<>();

        calculators.forEach(calculator -> {

            Task task = new Task(
                    calculator.getName(),
                    calculator.getQuestionTemplate(),
                    calculator.getThemeValues(),
                    TaskTypeValues.OPEN_ANSWER);
            tasksByTheme.get(calculator.getThemeValues()).add(task);
            taskTemplatesByName.put(calculator.getName(), task);

            calculatorsByName.put(calculator.getName(), calculator);
        });
    }

    @Override
    public List<Task> getCalculatorsByTheme(ThemeValues themeValue) {
        log.info("'getCalculatorsByTheme' invoked with params'{}'", themeValue);

        log.info("'tasksByTheme={}'", tasksByTheme);

        return tasksByTheme.get(themeValue);
    }


    @Override
    public OpenAnswerTask calculate(String taskName, Map<String, Object> inputParams, String teacherEmail) {
        log.info("'calculate' invoked with params'{}, {}, {}'", taskName, inputParams, teacherEmail);

        if (!calculatorsByName.containsKey(taskName)) {
            throw new ResourceNotFoundException("calculator with name'" + taskName + "' not found");
        }

        CalculatorImpl calculator = calculatorsByName.get(taskName);
        CalculationData calculatedValues = calculator.calculate(inputParams);
        String question = calculator.changePlaceHoldersToValues(calculator.getQuestionTemplate(), inputParams);
        String answer = calculator.changePlaceHoldersToValues(calculator.getAnswerTemplate(), calculatedValues.getCalculatedValues());

        OpenAnswerTask openAnswerTask = new OpenAnswerTask(
                calculator.getName(),
                question,
                calculator.getThemeValues(),
                TaskTypeValues.OPEN_ANSWER,
                calculatedValues.getCalculations(),
                answer,
                calculatedValues.getCalculatedValues());

        Teacher teacher = (Teacher) userRepository.findByEmail(teacherEmail);
        openAnswerTask.setTeacher(teacher);

        openAnswerTask = taskRepository.save(openAnswerTask);
        log.info("'openAnswerTask' after save'{}'", openAnswerTask);

        return openAnswerTask;
    }

    @Override
    public void checkAnswers() {

    }
}
