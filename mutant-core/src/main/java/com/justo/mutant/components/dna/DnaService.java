package com.justo.mutant.components.dna;

import com.justo.mutant.components.dna.stats.Stats;
import com.justo.mutant.log.Log;

public class DnaService {
    
    private final DnaRepositoryImpl dnaRepository;
    
    public DnaService(DnaRepositoryImpl dnaRepository) {
        this.dnaRepository = dnaRepository;
    }
    
    public Stats getStats() {
        long mutants = dnaRepository.countByMutant(true);
        long noMutants = dnaRepository.countByMutant(false);
        
        return new Stats(mutants, noMutants);
    }
    
    public Dna insert(String[] dna, boolean isMutant) {
        Dna dnaObject = new Dna();
        dnaObject.setDna(String.join("", dna));
        dnaObject.setMutant(isMutant);
        dnaObject = dnaRepository.insert(dnaObject);
        
        Log.DATA_ACCESS.info("Dna {} inserted", dnaObject);
        return dnaObject;
    }

}
