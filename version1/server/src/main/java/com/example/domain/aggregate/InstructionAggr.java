package com.example.domain.aggregate;

/**
 * @Author dongkw
 * @Date 2021/1/25、10:51 上午
 **/

import com.example.domain.aggregate.bean.CancelledEvent;
import com.example.domain.aggregate.bean.ConfirmEvent;
import com.example.domain.aggregate.bean.CreateEvent;
import com.example.domain.aggregate.bean.FailEvent;
import com.example.domain.aggregate.bean.command.CancelledCmd;
import com.example.domain.aggregate.bean.command.ConfirmCmd;
import com.example.domain.aggregate.bean.command.CreateCmd;
import com.example.domain.aggregate.bean.command.FailCmd;
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

    @CommandHandler
    public InstructionAggr(CreateCmd cmd) {
        CreateEvent event = new CreateEvent(cmd);
//        CreateEvent event = new CreateEvent(cmd.getId(), cmd.getData());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CreateEvent event) {
        this.id = event.getId();
        this.data = event.getData();
    }

    @CommandHandler
    public void handler(CancelledCmd cmd) {
        CancelledEvent event = new CancelledEvent(cmd.getId(), cmd.getData());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CancelledEvent event) {
        this.data = event.getData();
    }

    @CommandHandler
    public void handler(FailCmd cmd) {
        CancelledEvent event = new CancelledEvent(cmd.getId(), data);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FailEvent event) {

    }

    @CommandHandler
    public void handler(ConfirmCmd cmd) {
        ConfirmEvent event = new ConfirmEvent(cmd.getId(), cmd.getData());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ConfirmEvent event) {

    }
}
