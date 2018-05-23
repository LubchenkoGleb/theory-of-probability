package com.kpi.diploma.perevertailo.model.dto;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TestDto {

    private Test test;

    private Teacher teacher;

    private List<Group> groups;

    private List<Task> task;
}
