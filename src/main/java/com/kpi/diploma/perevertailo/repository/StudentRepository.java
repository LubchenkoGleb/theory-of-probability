package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.user.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> getAllByGroupId(String groupId);
}
