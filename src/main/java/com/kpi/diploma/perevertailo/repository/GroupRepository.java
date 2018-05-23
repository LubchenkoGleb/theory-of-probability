package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<Group, String> {

    List<Group> findAllByTeacherId(String teacherId);


}
