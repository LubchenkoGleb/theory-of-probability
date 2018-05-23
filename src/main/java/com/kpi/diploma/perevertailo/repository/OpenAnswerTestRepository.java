package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.task.OpenAnswerTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OpenAnswerTestRepository extends MongoRepository<OpenAnswerTask, String> {
}
