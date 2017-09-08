package com.justo.mutant.api.rest.mutant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.justo.mutant.api.rest.Paths;
import com.justo.mutant.api.rest.mutant.to.MutantRequest;
import com.justo.mutant.components.mutant.MutantService;

@RestController
public class MutantController {
    
    @Autowired
    private MutantService mutantService;
    
    @RequestMapping(path = Paths.MUTANT, method = RequestMethod.POST)
    public ResponseEntity<Boolean> checkMutant(@RequestBody MutantRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("We need request to check if it's a mutant");
        }
        
        return mutantService.isMutant(request.getDna()) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
