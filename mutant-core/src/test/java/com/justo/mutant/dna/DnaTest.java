package com.justo.mutant.dna;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

import com.justo.mutant.MainTest;
import com.justo.mutant.components.dna.Dna;
import com.justo.mutant.components.dna.DnaService;
import com.justo.mutant.components.dna.stats.Stats;


@ActiveProfiles({"test"})
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.DEFAULT)
public class DnaTest extends MainTest {

    @Autowired
    private DnaService dnaService;
    
    @Before
    @After
    public void clean() {
        
    }
    
    @Test
    public void persistTest() {
        Dna dna = dnaService.insert(new String[]{"ATAA", "ATTT", "AGCT", "ATTT"}, true);
        Assert.assertNotNull(dna);
        Assert.assertEquals(true, dna.isMutant());
        
        Stats stats = dnaService.getStats();
        Assert.assertEquals(1, stats.getnMutants());
        Assert.assertEquals(0, stats.getnNonMutants());
    }
    
    @Test
    public void persistManyTest() {
        Dna dna = dnaService.insert(new String[]{"ATAA", "ATTT", "AGCT", "ATTT"}, true);
        Dna dna2 = dnaService.insert(new String[]{"ATAA", "ATTT", "AGCT", "ATTT"}, true);
        Assert.assertNotNull(dna);
        Assert.assertEquals(true, dna2.isMutant());
        Assert.assertNotNull(dna);
        Assert.assertEquals(true, dna2.isMutant());
        
        Stats stats = dnaService.getStats();
        Assert.assertEquals(1, stats.getnMutants());
        Assert.assertEquals(0, stats.getnNonMutants());
    }
}

