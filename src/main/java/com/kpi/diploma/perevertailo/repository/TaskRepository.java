package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.task.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
