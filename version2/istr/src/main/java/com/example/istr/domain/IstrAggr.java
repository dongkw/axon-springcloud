package com.example.istr.domain;

import com.example.istr.model.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class IstrAggr {

    private String aggrId;
    private IstrStatus status;


    @CommandHandler
    public IstrAggr(IssueCmd cmd) {

        AggregateLifecycle.apply(new IssueEvt(cmd.getAggrId()));
    }

    @CommandHandler
    public void handler(ApprovalCmd cmd) {
        if (status == IstrStatus.ISSUE)
            AggregateLifecycle.apply(new ApprovalEvt(cmd.getAggrId()));
    }

    @CommandHandler
    public void handler(ApprovalFailCmd cmd) {
        if (status == IstrStatus.ISSUE)
            AggregateLifecycle.apply(new ApprovalFailEvt(cmd.getAggrId()));
    }

    @CommandHandler
    public void handler(DistributeCmd cmd) {
        if (status == IstrStatus.APPROVE)
            AggregateLifecycle.apply(new DistributeEvt(cmd.getAggrId()));
    }

    @CommandHandler
    public void handler(DistributeFailCmd cmd) {
        if (status == IstrStatus.APPROVE)
            AggregateLifecycle.apply(new DistributeFailEvt(cmd.getAggrId()));
    }

    @CommandHandler
    public void handler(ExecuteCmd cmd) {
        if (status == IstrStatus.DISTRIBUTE)
            AggregateLifecycle.apply(new ExecuteEvt(cmd.getAggrId()));
    }

    @CommandHandler
    public void handler(FailCmd cmd) {
        AggregateLifecycle.apply(new FailEvt(cmd.getAggrId()));
    }


    @EventSourcingHandler
    public void on(IssueEvt evt) {
        this.aggrId = evt.getAggrId();
        this.status = IstrStatus.ISSUE;
    }


    @EventSourcingHandler
    public void on(ApprovalEvt evt) {
        this.status = IstrStatus.APPROVE;
    }

    @EventSourcingHandler
    public void on(ApprovalFailEvt evt) {

    }

    @EventSourcingHandler
    public void on(DistributeEvt evt) {
        this.status = IstrStatus.DISTRIBUTE;
    }

    @EventSourcingHandler
    public void on(DistributeFailEvt evt) {

    }

    @EventSourcingHandler
    public void on(ExecuteEvt evt) {
        this.status = IstrStatus.EXECUTE;
    }

    @EventSourcingHandler
    public void on(FailEvt evt) {

    }
}
