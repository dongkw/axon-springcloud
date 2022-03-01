package com.example.controller;

import com.example.domain.aggregate.bean.CreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2021/1/26、11:23 上午
 **/
@Slf4j
@Component
@ProcessingGroup(value = "event-group")
public class GatewayHandler2 {

    @Autowired
    private EventGateway eventGateway;


    @EventHandler
    public void handler(CreateEvent event) {
        log.info("aaaaa {}", event);
    }
}
