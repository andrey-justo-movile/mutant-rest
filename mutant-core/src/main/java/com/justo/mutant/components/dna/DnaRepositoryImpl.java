package com.justo.mutant.components.dna;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


public class DnaRepositoryImpl {
    
    private static final String DNA_COLLECTION_NAME = "dna";
    private final MongoTemplate template;
    
    public DnaRepositoryImpl(MongoTemplate template) {
        this.template = template;
    }

    public Long countByMutant(Boolean isMutant) {
        Query query = new Query(Criteria.where("mutant").is(isMutant));
        return template.count(query, DNA_COLLECTION_NAME);
    }
    
    public Dna insert(Dna dnaObject) {
        template.insert(dnaObject, DNA_COLLECTION_NAME);
        return dnaObject;
    }
}

