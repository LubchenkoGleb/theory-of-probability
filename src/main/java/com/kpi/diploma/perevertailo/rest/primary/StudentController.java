package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.dto.StudentDto;
import com.kpi.diploma.perevertailo.service.primary.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    final StudentService studentService;

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
}
