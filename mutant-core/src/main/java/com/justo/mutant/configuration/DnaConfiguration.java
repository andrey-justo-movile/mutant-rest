package com.justo.mutant.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.justo.mutant.components.dna.DnaRepositoryImpl;
import com.justo.mutant.components.dna.DnaService;

@Configuration
public class DnaConfiguration {
    
    @Bean
    @Autowired
    public DnaRepositoryImpl dnaRepository(MongoTemplate template) {
        return new DnaRepositoryImpl(template);
    }
    
    @Bean
    @Autowired
    public DnaService dnaService(DnaRepositoryImpl dnaRepository) {
        return new DnaService(dnaRepository);
    }
    
}
