package com.example.domain.aggregate;

/**
 * @Author dongkw
 * @Date 2021/1/25、10:51 上午
 **/

import com.example.domain.aggregate.bean.command.CancelledCmd;
import com.example.domain.aggregate.bean.command.ConfirmCmd;
import com.example.domain.aggregate.bean.command.CreateCmd;
import com.example.domain.aggregate.bean.command.FailCmd;
import com.example.domain.aggregate.bean.event.CancelledEvent;
import com.example.domain.aggregate.bean.event.ConfirmEvent;
import com.example.domain.aggregate.bean.event.CreateEvent;
import com.example.domain.aggregate.bean.event.FailEvent;
import com.example.domain.aggregate.bean.status.Status;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class InstructionAggr {

    @AggregateIdentifier
    private String id;

    private String data;

    private Status status;


    @CommandHandler
    public InstructionAggr(CreateCmd cmd) {
        CreateEvent event = new CreateEvent();
        event.setId(cmd.getId());
        event.setData(cmd.getData());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateEvent event) {
        this.id = event.getId();
        this.data = event.getData();
    }

    @CommandHandler
    public void handler(CancelledCmd cmd) {
        CancelledEvent event = new CancelledEvent();
        event.setId(cmd.getId());
        event.setData(cmd.getData());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CancelledEvent event) {
        this.data = event.getData();
    }

    @CommandHandler
    public void handler(FailCmd cmd) {
        CancelledEvent event = new CancelledEvent();
        event.setId(cmd.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FailEvent event) {

    }

    @CommandHandler
    public void handler(ConfirmCmd cmd) {
        ConfirmEvent event = new ConfirmEvent();
        event.setId(cmd.getId());
        event.setData(cmd.getData());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ConfirmEvent event) {

    }
}
