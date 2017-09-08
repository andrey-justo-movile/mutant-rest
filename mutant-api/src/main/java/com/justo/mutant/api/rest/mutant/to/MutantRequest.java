package com.justo.mutant.api.rest.mutant.to;

import java.util.List;

public class MutantRequest {

    private List<String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        return "MutantRequest {dna=" + dna + "}";
    }

}
