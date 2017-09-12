package com.justo.mutant.api.rest.handler;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.justo.mutant.api.MainTest;
import com.justo.mutant.api.rest.Paths;
import com.justo.mutant.api.rest.mutant.to.MutantRequest;

@ActiveProfiles("test")
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.DEFAULT)
public class ExceptionHandlerTest extends MainTest {

    @Test
    public void checkNotFoundTest() {
        ResponseEntity<Void> response = this.restTemplate.getForEntity("http://localhost:" + port + "/not_found", Void.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void checkNotFoundWithQueryTest() {
        ResponseEntity<Void> response = this.restTemplate.getForEntity("http://localhost:" + port + "/not_found?test1=1&test2=2", Void.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
    public void checkIllegalArgumentsTest() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTAGT", "AGAGGG", "CTCCTA", "TCACTG"};
        MutantRequest request = new MutantRequest(Arrays.asList(dna));
        ResponseEntity<Void> response = this.restTemplate.postForEntity("http://localhost:" + port + Paths.MUTANT, request, Void.class);
        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

}
