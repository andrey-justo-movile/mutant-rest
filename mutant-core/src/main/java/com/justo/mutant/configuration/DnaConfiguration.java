package com.justo.mutant.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.justo.mutant.components.dna.DnaRepository;
import com.justo.mutant.components.dna.DnaService;

public class DnaConfiguration {

    @Bean
    @Autowired
    public DnaService dnaService(DnaRepository dnaRepository) {
        return new DnaService(dnaRepository);
    }
    
}
