package com.kpi.diploma.perevertailo.model.document.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Role;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@TypeAlias("student")
@Data
@ToString(exclude = {"group"}, callSuper = true)
public class Student extends User {

    @DBRef(lazy = true)
    @JsonIgnore
    private Group group;

    public Student() {
        super();
    }

    public Student(String email, String inviteKey, Role role) {
        super(email, inviteKey, role);
    }
}
