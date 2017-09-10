package com.justo.mutant.api.rest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.justo.mutant.api.MainTest;

@ActiveProfiles("test")
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.DEFAULT)
public class HealthControllerTest extends MainTest {

    @Test
    public void healthTest() {
        ResponseEntity<Void> response = this.restTemplate.getForEntity("http://localhost:" + port + Paths.HEALTH, Void.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
