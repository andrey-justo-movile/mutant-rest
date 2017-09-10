package com.justo.mutant.components.dna;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Dna implements Serializable, Cloneable {

    private static final long serialVersionUID = 942090330252095159L;

    @Id
    private String id;
    private List<String> dna;
    private boolean mutant;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }

    @Override
    protected Dna clone() throws CloneNotSupportedException {
        return (Dna) super.clone();
    }

    @Override
    public String toString() {
        return "Dna {id=" + id + ", dna=" + dna + ", mutant=" + mutant + "}";
    }

}
