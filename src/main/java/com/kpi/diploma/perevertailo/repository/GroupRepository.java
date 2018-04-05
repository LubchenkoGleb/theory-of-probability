package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {


}
