package com.justo.mutant.components.dna.stats;

public class Stats {

    private final long nMutants;
    private final long nNonMutants;

    public Stats(long nMutants, long nNonMutants) {
        this.nMutants = nMutants;
        this.nNonMutants = nNonMutants;
    }

    public long getnMutants() {
        return nMutants;
    }

    public long getnNonMutants() {
        return nNonMutants;
    }

    @Override
    public String toString() {
        return "Stats {nMutants=" + nMutants + ", nNonMutants=" + nNonMutants + "}";
    }

}
