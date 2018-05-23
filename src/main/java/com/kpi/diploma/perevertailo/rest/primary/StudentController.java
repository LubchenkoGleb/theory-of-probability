package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.document.task.Answer;
import com.kpi.diploma.perevertailo.model.document.task.PassedTestResponse;
import com.kpi.diploma.perevertailo.model.document.task.TestResult;
import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.dto.StudentDto;
import com.kpi.diploma.perevertailo.model.dto.TestDto;
import com.kpi.diploma.perevertailo.model.util.security.MongoUserDetails;
import com.kpi.diploma.perevertailo.service.primary.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/get-by-group/{groupId}")
    private ResponseEntity<List<Student>> getByGroup(@PathVariable String groupId) {
        log.info("'getByGroup' invoked with params'{}'", groupId);

        List<Student> byGroup = studentService.getByGroup(groupId);

        return ResponseEntity.ok(byGroup);
    }

    @GetMapping(value = "/all")
    private ResponseEntity<List<StudentDto>> getAll() {
        log.info("'getAll' invoked");

        List<StudentDto> all = studentService.getAllStudents();

        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/all-without-group")
    private ResponseEntity<List<Student>> getAllWithoutGroup() {
        return ResponseEntity.ok(studentService.getStudentsWithoutGroup());
    }

    @PostMapping(value = "/pass-test/{testId}")
    private ResponseEntity<PassedTestResponse> passTest(@AuthenticationPrincipal MongoUserDetails principal,
                                                        @PathVariable String testId, @RequestBody List<Answer> answers) {

        return ResponseEntity.ok(studentService.passTest(principal.getUserId(), testId, answers));
    }

    @GetMapping(value = "/passed-tests")
    private ResponseEntity<List<TestResult>> getPassedTests(@AuthenticationPrincipal MongoUserDetails principal) {
        return ResponseEntity.ok(studentService.getPassedTests(principal.getUserId()));
    }

    @GetMapping(value = "/not-passed-tests")
    private ResponseEntity<List<Test>> getNotPassedTests(@AuthenticationPrincipal MongoUserDetails principal) {
        return ResponseEntity.ok(studentService.getNotPassedTests(principal.getUserId()));
    }

    @GetMapping(value = "/get-test/{testId}")
    private ResponseEntity<TestDto> getTest(@PathVariable String testId) {
        return ResponseEntity.ok(studentService.getTestWitQuestions(testId));
    }
}
