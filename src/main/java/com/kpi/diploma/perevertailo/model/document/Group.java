package com.kpi.diploma.perevertailo.model.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"students", "teacher", "tests"})
@EqualsAndHashCode(exclude = {"students", "teacher", "tests"})
public class Group {

    @Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    @JsonIgnore
    private List<Student> students;

    @DBRef
    @JsonIgnore
    private Teacher teacher;

    @DBRef
    @JsonIgnore
    private List<Test> tests;

    public Group() {
        this.students = new ArrayList<>();
        this.tests = new ArrayList<>();
    }
}
