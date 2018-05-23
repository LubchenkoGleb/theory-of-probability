package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.task.OpenAnswerTask;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.primary.OpenAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/open-answer-task")
public class OpenAnswerTaskController {

    private final OpenAnswerService openAnswerService;

    public OpenAnswerTaskController(OpenAnswerService openAnswerService) {
        this.openAnswerService = openAnswerService;
    }

    @GetMapping(value = "/get-by-theme/{theme}")
    private ResponseEntity<List<Task>> getByTheme(@PathVariable String theme) {
        log.info("'getByTheme' invoked with params'{}'", theme);

        List<Task> calculatorsByTheme = openAnswerService.getCalculatorsByTheme(ThemeValues.valueOf(theme));

        return ResponseEntity.ok(calculatorsByTheme);

    }

    @PostMapping(value = "/calculate/{taskName}")
    private ResponseEntity<OpenAnswerTask> calculate(@RequestBody Map<String, Object> inputData,
                                                     @PathVariable String taskName, Principal principal) {
        log.info("'calculate' invoked with params'{}'", inputData);

        OpenAnswerTask calculate = openAnswerService.calculate(taskName, inputData, principal.getName());
        log.info("'calculate={}'", calculate);

        return ResponseEntity.ok(calculate);
    }
}
