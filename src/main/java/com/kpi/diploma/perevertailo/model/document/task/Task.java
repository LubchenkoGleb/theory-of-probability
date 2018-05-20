package com.kpi.diploma.perevertailo.model.document.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.util.value.TaskTypeValues;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "task")
@ToString(exclude = {"teacher"})
public class Task {

    @Id
    private String id;

    private String name;

    private String question;

    private ThemeValues theme;

    private TaskTypeValues type;

    @DBRef(lazy = true)
    @JsonIgnore
    private Teacher teacher;

    public Task() {
    }

    public Task(String name, String question, ThemeValues theme, TaskTypeValues type) {
        this.name = name;
        this.question = question;
        this.theme = theme;
        this.type = type;
    }
}
