package com.justo.mutant.api;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.justo.mutant.configuration.DnaConfiguration;
import com.justo.mutant.configuration.MutantConfiguration;
import com.justo.mutant.test.api.configuration.MongoTestConfiguration;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ComponentScan("com.justo.mutant.api")
@SpringBootTest(classes = {MutantConfiguration.class, DnaConfiguration.class, MongoTestConfiguration.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public abstract class MainTest {

    @LocalServerPort
    protected int port;
    
    @Autowired
    protected TestRestTemplate restTemplate;

}
