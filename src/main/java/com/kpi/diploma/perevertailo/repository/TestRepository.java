package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestRepository extends MongoRepository<Test, String> {

    List<Test> findAllByIdNotIn(List<String> excludeIds);
}
