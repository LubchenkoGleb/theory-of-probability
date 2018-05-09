package com.kpi.diploma.perevertailo.repository;


import com.kpi.diploma.perevertailo.model.document.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    List<User> findAllByEmailIn(List<String> emails);

    List<User> findAllByIdIn(List<String> ids);

}
