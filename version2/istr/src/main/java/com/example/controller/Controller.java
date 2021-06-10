package com.example.controller;

import com.example.basic.Factory;
import com.example.pledge.Pledge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @PostMapping("create/pledge")
    public ResponseEntity createPledge(@RequestBody Pledge pledge) {
        Factory.getFactory(Pledge.class.getSimpleName()).buildCreateCmd(pledge);
        return ResponseEntity.ok().build();
    }

}
