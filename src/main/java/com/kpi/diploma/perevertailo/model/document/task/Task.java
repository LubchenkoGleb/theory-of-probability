package com.kpi.diploma.perevertailo.model.document.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Map;

@Data
@ToString(exclude = {"teacher"})
public class Task {

    @Id
    private String id;

    private String name;

    private String fullName;

    private String question;

    private ThemeValues theme;

    private TaskTypeValues type;

    @DBRef
    @JsonIgnore
    private Teacher teacher;

    public Task(String name, String fullName, String question, ThemeValues theme, TaskTypeValues type) {
        this.name = name;
        this.fullName = fullName;
        this.question = question;
        this.theme = theme;
        this.type = type;
    }
}
