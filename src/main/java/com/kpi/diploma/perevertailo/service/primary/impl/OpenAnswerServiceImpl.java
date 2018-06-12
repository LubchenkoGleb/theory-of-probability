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
import com.kpi.diploma.perevertailo.service.util.calculator.impl.actions.TaskFiveCalculator;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.basicNumerical.CharactFirst;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.bernuli.*;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.complete.TaskSixCalculator;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.definition.TaskFourCalculator;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.definition.TaskOneCalculator;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.definition.TaskThreeCalculator;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.definition.TaskTwoCalculator;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.statistical.Kolmagorov;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.statistical.Pirson;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.range.Distribution;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.range.MathExp;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.selective.SelectiveMethod;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.statistical.Batler;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.statistical.Kohren;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.statistical.StatisticHipotes;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.systemOfTwo.SystemTwo;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.variance.MostPropableNumber;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.variance.Variance;
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
                                 TaskFourCalculator taskFourCalculator,
                                 TaskFiveCalculator taskFiveCalculator,
                                 TaskSixCalculator taskSixCalculator,
                                 TaskSevenCalculator taskSevenCalculator,
                                 TaskEightCalculator taskEightCalculator,
                                 TaskNineCalculator taskNineCalculator,
                                 PuassonFormula puassonFormula,
                                 FormulaLaplassa formulaLaplassa,
                                 MostPropableNumber mostPropableNumber,
                                 Variance variance,
                                 MathExp mathExp,
                                 Distribution distribution,
                                 CharactFirst charactFirst,
                                 SystemTwo systemTwo,
                                 SelectiveMethod selectiveMethod,
                                 StatisticHipotes statisticHipotes,
                                 Batler batler,
                                 Kohren kohren,
                                 Kolmagorov kolmagorov,
                                 Pirson pirson,
                                 UserRepository userRepository,
                                 TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;

        List<CalculatorImpl> calculators = Arrays.asList(taskOneCalculator, taskTwoCalculator, taskThreeCalculator,
                taskFourCalculator, taskFiveCalculator, taskSixCalculator, taskSevenCalculator, taskEightCalculator,
                taskNineCalculator, puassonFormula, formulaLaplassa, mostPropableNumber, variance, mathExp,
                distribution, charactFirst, systemTwo, selectiveMethod, statisticHipotes, batler, kohren, kolmagorov,
                pirson);

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
        String questionToStudent = calculator.changePlaceHoldersToValues(calculator.getQuestionToStudentTemplate(), inputParams);
        String question = calculator.changePlaceHoldersToValues(calculator.getQuestionTemplate(), inputParams);

        Map<String, Object> tempMap = new HashMap<>();
        tempMap.putAll(calculatedValues.getCalculatedValues());
        tempMap.putAll(inputParams);

        String answer = calculator.changePlaceHoldersToValues(calculator.getAnswerTemplate(), tempMap);

        OpenAnswerTask openAnswerTask = new OpenAnswerTask(
                calculator.getName(),
                question,
                calculator.getThemeValues(),
                TaskTypeValues.OPEN_ANSWER,
                calculatedValues.getCalculations(),
                answer,
                calculatedValues.getCalculatedValues(),
                calculator.getAnswerTemplate(),
                questionToStudent);

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
