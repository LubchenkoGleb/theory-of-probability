package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.Theme;
import com.kpi.diploma.perevertailo.service.primary.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final ThemeService themeService;

    public TaskController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping(value = "/get-themes")
    private ResponseEntity<List<Theme>> getListThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }
}
