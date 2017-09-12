package com.justo.mutant.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.justo.mutant.MainTest;
import com.justo.mutant.components.dna.Dna;

@ActiveProfiles({"dev", "test"})
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.DEFAULT)
public class ParserTest extends MainTest {

    private final Mapper mapper = JacksonMapper.standardMapper();
    private final TypeReference<List<Dna>> DNA_RESULT_REFERENCE = new TypeReference<List<Dna>>() {};

    @Test
    public void toJsonTest() {
        String[] dna = {"AT", "CA"};

        String json = mapper.toJson(dna);

        Assert.assertEquals("[\"AT\",\"CA\"]", json);
    }
    
    @Test
    public void toPrettyJsonTest() {
        String[] dna = {"AT", "CA"};

        String json = mapper.toPrettyJson(dna);

        Assert.assertEquals("[ \"AT\", \"CA\" ]", json);
    }
    
    @Test
    public void fromListTest() {
        String json = "[{ \"dna\":\"ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG\", \"mutant\": \"true\"}]";

        List<Dna> dnas = mapper.readJson(json, DNA_RESULT_REFERENCE);
        Assert.assertNotNull("Dnas can't be null", dnas);
        Assert.assertEquals(1, dnas.size());
    }

    @Test
    public void fromJsonTest() {
        String json = "{ \"dna\":\"ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG\", \"mutant\": \"true\"}";

        Dna dna = mapper.readJson(json, Dna.class);
        Assert.assertNotNull("Dna can't be null", dna);
        Assert.assertTrue("This dna is a mutant", dna.isMutant());
        Assert.assertEquals("ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG", dna.getDna());
    }

}
