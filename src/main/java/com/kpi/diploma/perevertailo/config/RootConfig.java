package com.kpi.diploma.perevertailo.config;

import com.sendgrid.SendGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Slf4j
@Configuration
@EnableMongoAuditing
public class RootConfig {

    @Value("${sendgrid.key}")
    private String sendGridKey;

    @Bean
    public SendGrid getSendGrid() {
        return new SendGrid(sendGridKey);
    }
}
