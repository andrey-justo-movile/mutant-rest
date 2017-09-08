package com.justo.mutant.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.justo.mutant.configuration.MutantConfiguration;

@Configuration
@Import({MutantConfiguration.class})
public class CoreConfiguration {

}
