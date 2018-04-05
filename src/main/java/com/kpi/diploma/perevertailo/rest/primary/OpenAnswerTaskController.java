package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.primary.OpenAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
