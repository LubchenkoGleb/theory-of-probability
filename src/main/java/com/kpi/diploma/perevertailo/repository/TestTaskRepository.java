package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.task.TestTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestTaskRepository extends MongoRepository<TestTask, String> {
}
