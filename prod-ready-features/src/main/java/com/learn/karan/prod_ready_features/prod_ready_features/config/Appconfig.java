package com.learn.karan.prod_ready_features.prod_ready_features.config;

import com.learn.karan.prod_ready_features.prod_ready_features.auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorImpl")
public class Appconfig {

    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }
    @Bean
    AuditorAware<String> getAuditorImpl(){
        return new AuditorAwareImpl();
    }
}
