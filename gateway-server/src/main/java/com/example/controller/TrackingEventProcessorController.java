package com.example.controller;

import com.example.config.TrackingEventProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dongkw
 * @Date 2021/3/18、4:15 下午
 **/
@RestController
public class TrackingEventProcessorController {

    @Autowired
    public TrackingEventProcessorService trackingEventProcessorService;


    @GetMapping("replay")
    public ResponseEntity replay(@RequestParam("name") String name, @RequestParam("index") Long index) {
        trackingEventProcessorService.replay(name, index);
        return ResponseEntity.ok().build();
    }
}
