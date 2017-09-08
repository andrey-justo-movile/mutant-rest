package com.justo.mutant;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

import com.justo.mutant.components.mutant.MutantService;

import org.junit.*;


@ActiveProfiles({"dev", "test"})
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MutantTest extends MainTest {

    @Autowired
    private MutantService mutantService;
    
    @Before
    @After
    public void clean() {
        
    }
    
    @Test
    public void simpleTest() {
        String[] dna = {"ATGCGA",
                        "CAGTGC",
                        "TTATGT",
                        "AGAAGG",
                        "CCCCTA",
                        "TCACTG"};
        Assert.assertEquals(mutantService.isMutant(dna), true);
    }
    
    @Test
    public void invertedDiagonalTest() {
        String[] dna = {"ATGCGA",
                        "CCGCGC",
                        "TTCTGT",
                        "ACAAGG",
                        "CCTCTA",
                        "TCACTG"};
        Assert.assertEquals(mutantService.isMutant(dna), true);
    }
}

