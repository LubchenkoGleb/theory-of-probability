package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, String> {

}
