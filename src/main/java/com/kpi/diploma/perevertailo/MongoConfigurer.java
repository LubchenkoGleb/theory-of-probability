package com.kpi.diploma.perevertailo;

import com.kpi.diploma.perevertailo.config.mongo.InheritanceAwareMongoRepositoryFactoryBean;
import com.kpi.diploma.perevertailo.config.mongo.InheritanceAwareSimpleMongoRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(repositoryBaseClass = InheritanceAwareSimpleMongoRepository.class,
        repositoryFactoryBeanClass = InheritanceAwareMongoRepositoryFactoryBean.class)
public class MongoConfigurer {

}