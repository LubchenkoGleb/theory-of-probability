package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.user.User;
import com.kpi.diploma.perevertailo.model.dto.UserDto;
import com.kpi.diploma.perevertailo.model.util.exception.IncorrectInputDataException;
import com.kpi.diploma.perevertailo.repository.UserRepository;
import com.kpi.diploma.perevertailo.service.primary.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean existByEmail(String email) {
        log.info("'existByEmail' invoked with params'{}'", email);
        User userByEmail = userRepository.findByEmail(email);
        log.info("returned'{}'", userByEmail);
        return userByEmail == null;
    }

    @Override
    public UserDto signUp(User user) {
        log.info("'signUp' invoked with params'{}'", user);

        User userFromDb = userRepository.findByEmail(user.getEmail());
        log.info("'userFromDb={}'", userFromDb);

        if (userFromDb == null || !userFromDb.getInviteKey().equals(user.getInviteKey())) {
            throw new IncorrectInputDataException("user by email not found or invite key is incorrect");
        } else if (userFromDb.getEnable()) {
            throw new IncorrectInputDataException("user already enabled");
        }

        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));
        userFromDb.setEnable(true);
        userFromDb = userRepository.save(userFromDb);

        return new UserDto(userFromDb, userFromDb.getRoles());
    }
}
