package com.justo.mutant.components.dna;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DnaRepository extends MongoRepository<Dna, String> {
    
    public Long countByMutant(boolean isMutant);

}
