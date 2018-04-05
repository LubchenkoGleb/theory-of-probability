package com.kpi.diploma.perevertailo.service.primary;

import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<Student> getByGroup(String groupId);

    List<StudentDto> getAllStudents();
}
