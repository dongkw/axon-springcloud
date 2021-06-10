package com.example.gateway;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Handler {
    @Autowired
    private EventGateway eventGateway;


    @EventHandler
    public void on(CmplCmd cmd) {
        eventGateway.publish(new CmplEvt(cmd.getAggrId()));
    }

    @EventHandler
    public void on(BookCmd cmd) {
        eventGateway.publish(new BookEvt(cmd.getAggrId()));
    }

    @EventHandler
    public void on(BondCmd cmd) {
        eventGateway.publish(new BondEvt(cmd.getAggrId()));
    }

}
