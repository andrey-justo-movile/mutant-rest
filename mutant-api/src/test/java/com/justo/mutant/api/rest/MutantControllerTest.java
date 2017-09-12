package com.justo.mutant.api.rest;

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
import com.justo.mutant.api.rest.mutant.to.MutantRequest;

@ActiveProfiles("test")
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.DEFAULT)
public class MutantControllerTest extends MainTest {

    @Test
    public void checkMutantTest() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        MutantRequest request = new MutantRequest(Arrays.asList(dna));
        ResponseEntity<Void> response = this.restTemplate.postForEntity("http://localhost:" + port + Paths.MUTANT, request, Void.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void checkHumanTest() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAGGG", "CTCCTA", "TCACTG"};
        MutantRequest request = new MutantRequest(Arrays.asList(dna));
        ResponseEntity<Void> response = this.restTemplate.postForEntity("http://localhost:" + port + Paths.MUTANT, request, Void.class);
        Assert.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
    
    @Test
    public void checkEmptyDataTest() {
        String[] dna = {};
        MutantRequest request = new MutantRequest(Arrays.asList(dna));
        ResponseEntity<Void> response = this.restTemplate.postForEntity("http://localhost:" + port + Paths.MUTANT, request, Void.class);
        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

}
