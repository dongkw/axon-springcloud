package com.example.controller;


import com.example.bean.BondCreateCmd;
import com.example.bean.PrCreateCmd;
import com.example.bean.UpdateCmd;
import com.example.bean.vo.Bond;
import com.example.bean.vo.Pledge;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:36 下午
 **/
@RestController
public class Controller {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("pledge")
    public ResponseEntity createPledge(@RequestBody PrCreateCmd cmd) {

        cmd.setAggregateId(UUID.randomUUID().toString());
        commandGateway.send(cmd);
        return ResponseEntity.ok().build();
    }

    @PostMapping("bond")
    public ResponseEntity createBond(@RequestBody BondCreateCmd cmd) {
        cmd.setAggregateId(UUID.randomUUID().toString());
        commandGateway.send(cmd);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("pledge")
    public ResponseEntity updatePledge(@RequestBody UpdateCmd<Pledge> cmd) {
        commandGateway.send(cmd);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("bond")
    public ResponseEntity updateBond(@RequestBody UpdateCmd<Bond> cmd) {
        commandGateway.send(cmd);
        return ResponseEntity.ok().build();
    }
}
