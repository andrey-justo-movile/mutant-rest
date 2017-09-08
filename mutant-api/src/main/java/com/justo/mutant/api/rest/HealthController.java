package com.justo.mutant.api.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping(path = Paths.HEALTH)
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok().build();
    }
    
    @RequestMapping(path = Paths.ERROR)
    public ResponseEntity<Void> errorCheck() {
        return ResponseEntity.badRequest().build();
    }
    
}
