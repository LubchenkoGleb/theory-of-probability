package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.dto.GroupDto;
import com.kpi.diploma.perevertailo.service.primary.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/invite-students")
    private ResponseEntity<List<String>> inviteStudents(@RequestBody List<String> studentEmails) {
        log.info("'inviteStudents' invoked with params'{}'", studentEmails);

        List<String> invitedEmails = adminService.inviteStudents(studentEmails);

        return ResponseEntity.ok(invitedEmails);
    }

    @PostMapping(value = "/invite-teachers")
    private ResponseEntity<List<String>> inviteTeachers(@RequestBody List<String> teachersEmails) {
        log.info("'inviteStudents' invoked with params'{}'", teachersEmails);

        List<String> invitedEmails = adminService.inviteTeachers(teachersEmails);

        return ResponseEntity.ok(invitedEmails);
    }

    @PostMapping(value = "/add-student-to-group")
    private ResponseEntity<GroupDto> addStudentToGroup(@RequestParam String studentId, @RequestParam String groupId) {
        log.info("'addStudentToGroup' invoked with params'{}, {}'", studentId, groupId);

        GroupDto groupDto = adminService.addStudentToGroup(studentId, groupId);
        log.info("'groupDto={}'", groupDto);

        return ResponseEntity.ok(groupDto);
    }

    @DeleteMapping(value = "/delete-student-from-group")
    private ResponseEntity<GroupDto> deleteStudentFromGroup(@RequestParam String studentId, @RequestParam String groupId) {
        log.info("'deleteStudentFromGroup' invoked with params'{}, {}'", studentId, groupId);

        GroupDto groupDto = adminService.deleteStudentFromGroup(studentId, groupId);
        log.info("'groupDto={}'", groupDto);

        return ResponseEntity.ok(groupDto);
    }

    @PostMapping(value = "/set-teacher-to-group")
    private ResponseEntity<GroupDto> setTeacherToGroup(@RequestParam String teacherId, @RequestParam String groupId) {
        log.info("'setTeacherToGroup' invoked with params'{}, {}'", teacherId, groupId);

        GroupDto groupDto = adminService.setTeacherToGroup(teacherId, groupId);
        log.info("'groupDto={}'", groupDto);

        return ResponseEntity.ok(groupDto);
    }
}
