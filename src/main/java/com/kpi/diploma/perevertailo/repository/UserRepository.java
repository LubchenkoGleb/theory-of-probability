package com.kpi.diploma.perevertailo.repository;


import com.kpi.diploma.perevertailo.model.document.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    List<User> findAllByEmailIn(List<String> emails);

    List<User> findAllByIdIn(List<String> ids);
}
