package com.example.controller;

import com.example.domain.aggregate.bean.command.CreateCmd;
import com.example.domain.aggregate.bean.event.CancelEvent;
import com.example.domain.aggregate.bean.event.CancelledEvent;
import com.example.domain.aggregate.bean.event.FailEvent;
import com.example.event.TestEvent;
import com.example.util.SyncController;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author dongkw
 * @Date 2021/1/25、11:08 上午
 **/
@Slf4j
@RestController
public class Controller extends SyncController {

    @Autowired
    private EventGateway eventGateway;

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CreateCmd cmd) {
        cmd.setId(UUID.randomUUID().toString());
        commandGateway.send(cmd);
//        Object result=waitResponse(cmd.getId());
        return ResponseEntity.ok().build();
    }
//    @EventHandler
//    public void handle(CmplSuccEvt event) {
//        log.info("return {}",event);
//        syncResponse(event.getId(), event);
//    }
//
//    @EventHandler
//    public void handle(CmplFailEvt event) {
//        log.info("return {}",event);
//        syncResponse(event.getId(), event);
//    }


    @PostMapping("/update")
    public ResponseEntity update() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancel")
    public ResponseEntity cancel(@RequestBody CancelEvent cancelEvent) {
        eventGateway.publish(cancelEvent);
        Object result = waitResponse(cancelEvent.getId());
        return ResponseEntity.ok(result);
    }

    @EventHandler
    public void handle(CancelledEvent event) {
        syncResponse(event.getId(), event);
    }

    @EventHandler
    public void handle(FailEvent event) {
        syncResponse(event.getId(), event);
    }


    @PostMapping("/test")
    public ResponseEntity test(@RequestBody TestEvent testEvent) {
        eventGateway.publish(testEvent);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/command")
    public String cmd() {
        return "success";
    }

}
