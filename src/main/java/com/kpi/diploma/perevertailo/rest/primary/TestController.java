package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.dto.CreateTestDto;
import com.kpi.diploma.perevertailo.model.dto.TestDto;
import com.kpi.diploma.perevertailo.model.util.security.MongoUserDetails;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.primary.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/by-group")
    private ResponseEntity<List<TestDto>> getByGroup(@RequestParam String groupId, @RequestParam ThemeValues theme) {
        return ResponseEntity.ok(testService.getAllByGroupAndTheme(groupId, theme));
    }

    @GetMapping(value = "/not-assigned-to-group")
    private ResponseEntity<List<TestDto>> notAssignedToGroup(@RequestParam String groupId, @RequestParam ThemeValues theme) {
        return ResponseEntity.ok(testService.getNotAssignedToGroupByTheme(groupId, theme));
    }

    @DeleteMapping(value = "/delete-from-group")
    private ResponseEntity<Test> deleteFromGroup(@RequestParam String groupId, @RequestParam String testId) {
        return ResponseEntity.ok(testService.deleteFromGroup(groupId, testId));
    }

    @PostMapping(value = "/add-test-to-group")
    private ResponseEntity<Test> addToGroup(@RequestParam String groupId, @RequestParam String testId) {
        return ResponseEntity.ok(testService.addToGroup(groupId, testId));
    }

    @PostMapping(value = "/create")
    private ResponseEntity<Test> create(@Valid  @RequestBody CreateTestDto createTestDto, @AuthenticationPrincipal MongoUserDetails details) {
        log.info("'create' params'{}, {}", createTestDto, details.getUserId());

        Test response = testService.createTest(createTestDto, details.getUserId());
        log.info("'response={}'", response);

        return ResponseEntity.ok(response);
    }
}
