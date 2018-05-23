package com.kpi.diploma.perevertailo.service.primary;


import com.kpi.diploma.perevertailo.model.document.user.User;
import com.kpi.diploma.perevertailo.model.dto.UserDto;

public interface UserService {

    boolean existByEmail(String email);

    UserDto signUp(User user);
}
