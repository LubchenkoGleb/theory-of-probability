package com.kpi.diploma.perevertailo.service.primary;

import com.kpi.diploma.perevertailo.model.dto.GroupDto;

import java.util.List;

public interface AdminService {

    List<String> inviteStudents(List<String> emails);

    List<String> inviteTeachers(List<String> emails);

    GroupDto addStudentToGroup(String studentId, String groupId);

    GroupDto deleteStudentFromGroup(String studentId, String groupId);

    GroupDto removeStudentsFromGroup(List<String> studentsIds, String groupId);

    GroupDto setTeacherToGroup(String teacherId, String groupId);

}
