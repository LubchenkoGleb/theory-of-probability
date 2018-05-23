package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends MongoRepository<Teacher, String> {

    List<Teacher> findAllByEnable(boolean enable);
}
