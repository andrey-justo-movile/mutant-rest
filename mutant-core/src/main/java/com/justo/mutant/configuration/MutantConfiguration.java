package com.justo.mutant.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.justo.mutant.components.mutant.MutantService;

@Configuration
public class MutantConfiguration {

    @Bean
    public MutantService mutantService() {
        return new MutantService(Arrays.asList("T", "C", "G", "A"), 4, 2);
    }

}
