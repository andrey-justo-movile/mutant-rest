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
@FixMethodOrder(MethodSorters.DEFAULT)
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
    public void simple2Test() {
        String[] dna = {"ATGCGA",
                        "CAGTGC",
                        "TTATGT",
                        "AGTCGG",
                        "CCCTTA",
                        "TCACTG"};
        Assert.assertEquals(mutantService.isMutant(dna), true);
    }
    
    @Test
    public void simple3Test() {
        String[] dna = {"AGGCGA",
                        "CAGGGC",
                        "TTATGT",
                        "AGGCGG",
                        "CCCTTA",
                        "TCACTG"};
        Assert.assertEquals(mutantService.isMutant(dna), true);
    }
    
    
    @Test
    public void simple4Test() {
        String[] dna = {"ATGCGAATGCGA",
                        "CAGTGCATACGC",
                        "TTATGTATGCGA",
                        "AGTCGGATACGC",
                        "CCCTTAATGCGA",
                        "CAGTTAATACGC",
                        "CCCGTAATGCGA",
                        "CAGTACCTGCGC",
                        "CCCTTAATACGA",
                        "CACGTACTGCGC",
                        "CCCTCAATACGA",
                        "TCACTGATGCGA"};
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
    
    @Test
    public void complexTest() {
        String[] dna = {"ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA",
                        "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC",
                        "TTATGTTTATGTTTATGTTTATGTTTATGTTTATGT",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
                        "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
                        "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTG"};
        Assert.assertEquals(mutantService.isMutant(dna), true);
    }
    
    @Test
    public void notMutantTest() {
        String[] notMutant = {
                        "ACGCG",
                        "TCTAG",
                        "GCACC",
                        "TATAG",
                        "GCGCG",
                        };
        Assert.assertEquals(mutantService.isMutant(notMutant), false);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void notValidDnaTest() {
        String[] notValidDna = {
                        "ACGCG",
                        "TCTA",
                        "GCACC",
                        "TAG",
                        "GCGCG",
                        };
        mutantService.isMutant(notValidDna);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void notValid2DnaTest() {
        String[] notValidDna = {
                        "ACGCG",
                        "TCTAB",
                        "GCACC",
                        "TAGYY",
                        "GCGCG",
                        };
        mutantService.isMutant(notValidDna);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void notValidDna3Test() {
        String[] notMutant = {
                        "",
                        "TCTAG",
                        "GCACC",
                        "TATAG",
                        "GCGCG",
                        };
        Assert.assertEquals(mutantService.isMutant(notMutant), false);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void notValidDna4Test() {
        String[] notMutant = {
                        "TCTAG",
                        "GCACC",
                        "TATAG",
                        "GCGCG",
                        };
        Assert.assertEquals(mutantService.isMutant(notMutant), false);
    }
    
    @Test
    public void worstCaseTest() {
        String[] begin = {"AT", "CG"};
        
        String[] endFrame = {
                        "ACGCG",
                        "TATAG",
                        "GCACG",
                        "TATAG",
                        "GCGCG",
                        };
        Assert.assertEquals(mutantService.isMutant(generateBigDna(begin, endFrame, 10000000)), true);
    }
    
    @Test
    public void worstCase2Test() {
        String[] begin = {"AT", "CG"};
        
        String[] endFrame = {
                        "ACGCG",
                        "TATAG",
                        "GCCCT",
                        "TATAG",
                        "GCGCG",
                        };
        Assert.assertEquals(mutantService.isMutant(generateBigDna(begin, endFrame, 1000000)), true);
    }
    
    private static String[] generateBigDna(String[] beginFrame, String[] endFrame, int iterations) {
        if (endFrame.length >= iterations || endFrame[0].length() >= iterations) {
            return endFrame;
        }
        
        String[] result = new String[iterations];
        int i = 0;
        int j = 0;
        for (i = 0; i < iterations; i++) {
            for (j = 0; j < iterations; j++) {
                if (result[i] == null) {
                    result[i] = beginFrame[i % beginFrame.length];
                } else {
                    result[i] += beginFrame[i % beginFrame.length];
                }
            }
        }
        
        for (i = 0; i < iterations; i++) {
            String currentItem = result[result.length - 1 - i];
            String currentFrameRow = endFrame[endFrame.length - 1 - i];
            result[result.length - 1 - i] = currentItem.substring(0, currentItem.length() - currentFrameRow.length()) + currentFrameRow;
        }
        
        return result;
    }
    
}

