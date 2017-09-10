package com.justo.mutant.components.dna;

import com.justo.mutant.components.dna.stats.Stats;

public class DnaService {
    
    private final DnaRepository dnaRepository;
    
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }
    
    public Stats getStats() {
        long mutants = dnaRepository.countByMutant(true);
        long noMutants = dnaRepository.countByMutant(false);
        
        return new Stats(mutants, noMutants);
    }

}
