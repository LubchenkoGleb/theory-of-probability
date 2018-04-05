package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
