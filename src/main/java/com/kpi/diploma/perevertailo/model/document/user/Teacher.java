package com.kpi.diploma.perevertailo.model.document.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Role;
import com.kpi.diploma.perevertailo.model.document.Test;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@TypeAlias("teacher")
@Document(collection = "user")
@JsonIgnoreProperties(value = {"target"})
@ToString(exclude = {"groups", "tests"}, callSuper = true)
@EqualsAndHashCode(exclude = {"groups", "tests"}, callSuper = true)
public class Teacher extends User {

    @DBRef(lazy = true)
    @JsonIgnore
    private Set<Group> groups;

    @DBRef(lazy = true)
    @JsonIgnore
    private List<Test> tests;

    public Teacher() {
        super();
        this.groups = new HashSet<>();
        this.tests = new ArrayList<>();
    }

    public Teacher(String email, String inviteKey, Role role) {
        super(email, inviteKey, role);
        this.groups = new HashSet<>();
        this.tests = new ArrayList<>();
    }
}
