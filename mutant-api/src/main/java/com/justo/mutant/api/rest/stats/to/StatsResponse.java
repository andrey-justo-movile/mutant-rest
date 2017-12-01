package com.justo.mutant.api.rest.stats.to;

public class StatsResponse {

    private final long countMutantDna;
    private final long countHumanDna;
    private final double ratio;

    public StatsResponse(long countMutantDna, long countHumanDna) {
        super();
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        if (countHumanDna == 0) {
            this.ratio = 0L;
        } else {
            this.ratio = (double) countMutantDna / (countMutantDna + countHumanDna);
        }
    }

    public long getCountMutantDna() {
        return countMutantDna;
    }

    public long getCountHumanDna() {
        return countHumanDna;
    }

    public double getRatio() {
        return ratio;
    }

    @Override
    public String toString() {
        return "StatusResponse {countMutantDna=" + countMutantDna + ", countHumanDna=" + countHumanDna + ", ratio=" + ratio + "}";
    }

}
