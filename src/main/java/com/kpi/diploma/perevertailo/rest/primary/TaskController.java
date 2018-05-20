package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.Theme;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.dto.CreateTaskDto;
import com.kpi.diploma.perevertailo.model.util.security.MongoUserDetails;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.primary.TaskService;
import com.kpi.diploma.perevertailo.service.primary.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final ThemeService themeService;

    private final TaskService taskService;

    public TaskController(ThemeService themeService, TaskService taskService) {
        this.themeService = themeService;
        this.taskService = taskService;
    }

    @GetMapping(value = "/get-themes")
    private ResponseEntity<List<Theme>> getListThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }

    @PostMapping(value = "/create")
    private ResponseEntity<Task> create(@Valid @RequestBody CreateTaskDto createTaskDto, @AuthenticationPrincipal MongoUserDetails principal) {
        log.info("'create' params'{}, {}'", createTaskDto, principal.getUsername());

        Task savedTask = taskService.createTask(createTaskDto, principal.getUserId());
        log.info("'savedTask={}'", savedTask);

        return ResponseEntity.ok(savedTask);
    }

    @GetMapping(value = "/get-by-theme/{theme}")
    private ResponseEntity<List<Task>> getByTheme(@PathVariable String theme, @AuthenticationPrincipal MongoUserDetails principal) {
        log.debug("'getByTheme' params'{}'", theme);

        return ResponseEntity.ok(taskService.getTaskByTheme(ThemeValues.valueOf(theme.toUpperCase()), principal.getUserId()));
    }

    @DeleteMapping(value = "/delete/{taskId}")
    private ResponseEntity<Task> deleteTask(@PathVariable String taskId, @AuthenticationPrincipal MongoUserDetails principal) {
        log.info("'deleteTask' params'{}, {}'", taskId, principal);

        Task response = taskService.deleteTask(taskId, principal.getUserId());
        log.info("'response={}'", response);

        return ResponseEntity.ok(response);
    }
}
