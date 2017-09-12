package com.justo.mutant.api.rest.mutant.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class MutantRequest {

    private final List<String> dna;

    @JsonCreator
    public MutantRequest(List<String> dna) {
        this.dna = dna;
    }

    public List<String> getDna() {
        return dna;
    }

    @Override
    public String toString() {
        return "MutantRequest {dna=" + dna + "}";
    }

}
