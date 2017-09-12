package com.justo.mutant.components.dna;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.justo.mutant.log.Log;


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
        try {
            template.insert(dnaObject, DNA_COLLECTION_NAME);
        } catch (DuplicateKeyException e) {
            Log.DATA_ACCESS.warn("This dna {} have been already checked", dnaObject, e);
        }
        return dnaObject;
    }
}

