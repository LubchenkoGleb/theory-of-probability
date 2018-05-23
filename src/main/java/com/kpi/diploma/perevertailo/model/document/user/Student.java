package com.kpi.diploma.perevertailo.model.document.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Role;
import com.kpi.diploma.perevertailo.model.document.task.TestResult;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
@TypeAlias("student")
@Data
@ToString(exclude = {"group"}, callSuper = true)
public class Student extends User {

    @DBRef(lazy = true)
    @JsonIgnore
    private Group group;

    private List<TestResult> testResults = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(String email, String inviteKey, Role role) {
        super(email, inviteKey, role);
    }
}
