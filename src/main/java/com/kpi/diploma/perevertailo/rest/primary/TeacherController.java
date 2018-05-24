package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.pojo.Journal;
import com.kpi.diploma.perevertailo.model.util.security.MongoUserDetails;
import com.kpi.diploma.perevertailo.service.primary.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService driverService) {
        this.teacherService = driverService;
    }

    @GetMapping(value = "/all")
    private ResponseEntity<List<Teacher>> getAll() {

        return ResponseEntity.ok(teacherService.getAll());
    }

    @GetMapping(value = "/gropus")
    private ResponseEntity<List<Group>> getGroups(@AuthenticationPrincipal MongoUserDetails principal) {
        return ResponseEntity.ok(teacherService.getListOfGroup(principal.getUserId()));
    }

    @GetMapping(value = "/journal/{groupId}")
    private ResponseEntity<Journal> getJournal(@PathVariable String groupId, @AuthenticationPrincipal MongoUserDetails details) {
        return ResponseEntity.ok(teacherService.getJournal(groupId, details.getUserId()));
    }
}
