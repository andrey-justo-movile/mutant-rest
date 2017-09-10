package com.justo.mutant.api.rest.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.justo.mutant.api.rest.Paths;
import com.justo.mutant.api.rest.stats.to.StatsResponse;
import com.justo.mutant.components.dna.DnaService;
import com.justo.mutant.components.dna.stats.Stats;

@RestController
public class StatstController {

    @Autowired
    private DnaService dnaService;
    
    
    @RequestMapping(path = Paths.STATS, method = RequestMethod.GET)
    public ResponseEntity<StatsResponse> stats() {
        Stats stats = dnaService.getStats();
        return ResponseEntity.ok().body(new StatsResponse(stats.getnMutants(), stats.getnNonMutants()));
    }

}
