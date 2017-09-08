package com.justo.mutant.components.mutant;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class MutantService {

    private final List<String> validChars;
    private final long seqSize;
    private final long minSeqs;

    public MutantService(List<String> validChars, long seqSize, long minSeqs) {
        this.validChars = validChars;
        this.seqSize = seqSize;
        this.minSeqs = minSeqs;
    }

    public boolean isMutant(String[] dna) {
        return isMutant(Arrays.asList(dna));
    }

    public boolean isMutant(List<String> dna) {
        if (CollectionUtils.isEmpty(dna) || dna.get(0).isEmpty()) {
            throw new IllegalArgumentException("The dna can't be empty");
        }

        int rows = dna.get(0).length();
        int cols = dna.size();
        if (rows != cols) {
            throw new IllegalArgumentException("The dna " + dna + " needs to be a square matrix");
        }

        int count = 0;
        for (int i = 0; i < rows - (seqSize - 1); i++) {
            for (int j = 0; j < cols - (seqSize - 1); j++) {
                if (StringUtils.isBlank(dna.get(i)) || dna.get(i).length() != rows) {
                    throw new IllegalArgumentException("The dna " + dna + " needs to be a square matrix");
                }

                boolean continueH = true;
                boolean continueV = true;
                boolean continueD = true;
                boolean continueInvertD = true;
                for (int k = i; k < i + (seqSize - 2) && k < j + (seqSize - 2); k++) {
                    if (!continueH || dna.get(i).charAt(j + k) != dna.get(i).charAt(j + k + 1)) {
                        continueH = false;
                    }

                    if (!continueV || dna.get(i + k).charAt(j) != dna.get(i + k + 1).charAt(j)) {
                        continueV = false;
                    }

                    if (!continueD || dna.get(i + k).charAt(j + k) != dna.get(i + k + 1).charAt(j + k + 1)) {
                        continueD = false;
                    }
                    
                    if (!continueInvertD || dna.get(rows - 1 - (i + k)).charAt(j + k) != dna.get(rows - 1 - (i + k + 1)).charAt(j + k + 1)) {
                        continueInvertD = false;
                    }
                }

                if (continueH) {
                    count++;
                }

                if (continueV) {
                    count++;
                }

                if (continueD) {
                    count++;
                }
                
                if (continueInvertD) {
                    count++;
                }
            }

            if (count >= minSeqs) {
                return true;
            }
        }

        return false;
    }

}
