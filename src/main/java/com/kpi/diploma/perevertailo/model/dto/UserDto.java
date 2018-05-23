package com.kpi.diploma.perevertailo.model.dto;

import com.kpi.diploma.perevertailo.model.document.Role;
import com.kpi.diploma.perevertailo.model.document.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {

    private User user;

    private Set<Role> roles;
}
