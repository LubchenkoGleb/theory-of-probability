package com.kpi.diploma.perevertailo.repository;

import com.kpi.diploma.perevertailo.model.document.Theme;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThemeRepository extends MongoRepository<Theme, String> {

    Theme findByValue(ThemeValues values);
}
