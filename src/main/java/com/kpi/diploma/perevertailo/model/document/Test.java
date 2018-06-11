package com.kpi.diploma.perevertailo.model.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpi.diploma.perevertailo.model.document.task.Task;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data
public class Test {

    @Id
    private String id;

    @JsonIgnore
    @DBRef(lazy = true)
    private Teacher teacher;

    @JsonIgnore
    @DBRef(lazy = true)
    private List<Group> groups;

    @JsonIgnore
    @DBRef(lazy = true)
    private List<Task> task;

    private String name;

    private ThemeValues theme;

    private String themeName;

    public Test() {
        this.groups = new ArrayList<>();
        this.task = new ArrayList<>();
    }
}
