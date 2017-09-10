package com.justo.mutant.components.mutant;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class MutantService {

    private final Pattern pattern;
    private final long seqSize;
    private final long minSeqs;

    public MutantService(List<String> validChars, long seqSize, long minSeqs) {
        this.seqSize = seqSize;
        this.minSeqs = minSeqs;
        
        String pattern = "[^";
        for (String currentChar: validChars) {
            pattern += currentChar;
        }
        pattern += "]";
        
        this.pattern = Pattern.compile(pattern);
    }

    public boolean isMutant(String[] dna) {
        validate(dna);

        int rows = dna[0].length();
        int cols = dna.length;

        int countH = 0;
        int countV = 0;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cols > j + 1 && dna[i].charAt(j) == dna[i].charAt(j + 1)) {
                    countH++;
                } else {
                    countH = 0;
                }

                if (rows > j + 1 && dna[j].charAt(i) == dna[j + 1].charAt(i)) {
                    countV++;
                } else {
                    countV = 0;
                }

                if (countV >= seqSize - 1) {
                    countV = 0;
                    count++;
                }

                if (countH >= seqSize - 1) {
                    countH = 0;
                    count++;
                }

                count += matchDiagonals(dna, rows, cols, i, j);
                if (count >= minSeqs) {
                    return true;
                }
            }
        }

        return false;
    }

    private int matchDiagonals(String[] matrix, int rows, int cols, int row, int col) {
        int countD = 0;
        int countInvertedD = 0;
        int count = 0;
        for (int k = 0; k < cols; k++) {
            if (cols > col + k + 1 && rows > row + k + 1 && matrix[row + k].charAt(col + k) == matrix[row + k + 1].charAt(col + k + 1)) {
                countD++;
            } else {
                countD = 0;
            }

            int analyzeCol = cols - 1 - k;
            if (rows > row + k + 1 && analyzeCol >= 0 && analyzeCol - col > 0
                    && matrix[row + k].charAt(analyzeCol - col) == matrix[row + k + 1].charAt(analyzeCol - col - 1)) {
                countInvertedD++;
            } else {
                countInvertedD = 0;
            }

            if (countD >= seqSize - 1) {
                countD = 0;
                count++;
            }

            if (countInvertedD >= seqSize - 1) {
                countInvertedD = 0;
                count++;
            }
        }

        return count;
    }

    private void validate(String[] dna) {
        if (dna == null || dna.length == 0 || dna[0].isEmpty()) {
            throw new IllegalArgumentException("The dna can't be empty");
        }
        
        for (String row : dna) {
            if (StringUtils.isBlank(row) || row.length() != dna.length) {
                throw new IllegalArgumentException("The dna " + dna + " needs to be a square matrix");
            }
            
            Matcher matcher = pattern.matcher(row);
            if (matcher.find()) {
                throw new IllegalArgumentException("The dna contains invalid letters for base check this " + row);
            }
            
        }
    }
}
