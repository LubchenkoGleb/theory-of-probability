package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.model.document.user.User;
import com.kpi.diploma.perevertailo.model.dto.UserDto;
import com.kpi.diploma.perevertailo.service.primary.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signUp")
    private ResponseEntity<UserDto> signUp(@RequestBody User user) {
        log.info("'signUp' invoked with params'{}'", user);

        UserDto userDto = userService.signUp(user);
        log.info("'userDto={}'", userDto);

        return ResponseEntity.ok(userDto);
    }
}
