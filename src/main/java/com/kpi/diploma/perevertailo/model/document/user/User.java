package com.kpi.diploma.perevertailo.model.document.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kpi.diploma.perevertailo.model.document.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "user")
@ToString(exclude = {"roles"})
@EqualsAndHashCode(exclude = {"roles"})
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;

    private Boolean enable = false;

    private String inviteKey;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private Set<Role> roles;

    public User() {
        roles = new HashSet<>();
    }

    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    public User(String email, String inviteKey, Role role) {
        this();
        this.email = email;
        this.inviteKey = inviteKey;
        roles.add(role);
    }

    public boolean hasRole(String role) {
        return this.roles.stream().anyMatch(r -> r.getRole().equals(role));
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
