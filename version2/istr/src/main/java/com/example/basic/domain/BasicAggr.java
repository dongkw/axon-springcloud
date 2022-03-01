package com.example.basic.domain;

import com.example.basic.Basic;
import com.example.basic.model.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class BasicAggr {

    private String aggrId;

    private Basic basic;

    private Status status;

    @CommandHandler
    public BasicAggr(CreateCmd cmd) {
        AggregateLifecycle.apply(new CreateEvt(cmd.getAggrId(), cmd.getIstr()));
    }

    @CommandHandler
    public void handler(CreateConfirmCmd cmd) {
        if (status == Status.CREATING)
            AggregateLifecycle.apply(new CreateConfirmEvt(cmd.getAggrId(), basic));
    }

    @CommandHandler
    public void handler(CreateFailCmd cmd) {
        if (status == Status.CREATING)
            AggregateLifecycle.apply(new CreateFailEvt(cmd.getAggrId(), basic));
    }

    @CommandHandler
    public void handler(UpdateCmd cmd) {
        AggregateLifecycle.apply(new UpdateEvt(cmd.getAggrId(), cmd.getIstr()));
    }

    @CommandHandler
    public void handler(UpdateConfirmCmd cmd) {
        if (status == Status.UPDATING)
            AggregateLifecycle.apply(new UpdateConfirmEvt(cmd.getAggrId(), basic));
    }

    @CommandHandler
    public void handler(UpdateFailCmd cmd) {
        if (status == Status.UPDATING)
            AggregateLifecycle.apply(new UpdateFailEvt(cmd.getAggrId(), cmd.getIstr()));
    }

    @CommandHandler
    public void handler(CancelCmd cmd) {
        AggregateLifecycle.apply(new CancelEvt(cmd.getAggrId(), basic));
    }

    @CommandHandler
    public void handler(CancelConfirmCmd cmd) {
        if (status == Status.CANCELLING)
            AggregateLifecycle.apply(new CancelConfirmEvt(cmd.getAggrId(), basic));
    }

    @CommandHandler
    public void handler(CancelFailCmd cmd) {
        if (status == Status.CANCELLING)
            AggregateLifecycle.apply(new CancelConfirmEvt(cmd.getAggrId(), basic));
    }

    @EventSourcingHandler
    public void on(CancelEvt evt) {
        this.status = Status.CANCELLING;
    }

    @EventSourcingHandler
    public void on(CancelConfirmEvt evt) {
        this.status = null;
    }

    @EventSourcingHandler
    public void on(CancelFailEvt evt) {
        this.status = null;
    }

    @EventSourcingHandler
    public void on(UpdateEvt evt) {
        this.basic = evt.getIstr();
        this.status = Status.UPDATING;
    }

    @EventSourcingHandler
    public void on(UpdateConfirmEvt evt) {
        this.status = null;
    }

    @EventSourcingHandler
    public void on(UpdateFailEvt evt) {
        this.status = null;
    }

    @EventSourcingHandler
    public void on(CreateEvt evt) {
        this.aggrId = evt.getAggrId();
        this.basic = evt.getIstr();
        this.status = Status.CREATING;
    }

    @EventSourcingHandler
    public void on(CreateConfirmEvt evt) {
        this.status = null;
    }

    @EventSourcingHandler
    public void on(CreateFailEvt evt) {
        this.status = null;
    }

}
