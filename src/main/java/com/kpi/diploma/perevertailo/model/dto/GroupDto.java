package com.kpi.diploma.perevertailo.model.dto;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
@AllArgsConstructor
public class GroupDto {

    private Group group;

    private List<Student> students;

    private Teacher teacher;

}
