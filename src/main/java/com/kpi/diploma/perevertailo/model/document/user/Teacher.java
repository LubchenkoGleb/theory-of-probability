package com.kpi.diploma.perevertailo.model.document.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "user")
@ToString(exclude = {"groups"}, callSuper = true)
@EqualsAndHashCode(exclude = {"groups"}, callSuper = true)
public class Teacher extends User {

    @DBRef
    @JsonIgnore
    private Set<Group> groups;

    public Teacher() {
        super();
        this.groups = new HashSet<>();
    }

    public Teacher(String email, String inviteKey, Role role) {
        super(email, inviteKey, role);
        this.groups = new HashSet<>();
    }
}
