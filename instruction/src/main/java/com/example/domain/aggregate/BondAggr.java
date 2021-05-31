package com.example.domain.aggregate;

import com.example.InstructionAggregate;
import com.example.bean.*;
import com.example.bean.vo.Bond;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:33 下午
 **/
@Aggregate
@NoArgsConstructor
@Slf4j
public class BondAggr extends InstructionAggregate<Bond> {
    @CommandHandler
    public BondAggr(BondCreateCmd cmd) {
        this.status.handler(this, cmd);
    }


    @Override
    public void create(CreateCmd<Bond> cmd) {
        AggregateLifecycle.apply(new BondCreateEvt(cmd.getInstruction()));
    }

    @Override
    public void update(UpdateCmd<Bond> cmd) {
        AggregateLifecycle.apply(new BondUpdateEvt(cmd.getInstruction()));
    }

    @Override
    public void cancel(CancelCmd<Bond> cmd) {
        AggregateLifecycle.apply(new BondCancelEvt(cmd.getInstruction()));
    }

    @Override
    public void approve(ApproveCmd<Bond> cmd) {
        AggregateLifecycle.apply(new BondApproveEvt(cmd.getInstruction()));
    }

    @Override
    public void distribute(DistributeCmd<Bond> cmd) {
        AggregateLifecycle.apply(new BondDistributeEvt(cmd.getInstruction()));
    }

    @Override
    public void cancelled(CancelledCmd cmd) {
        AggregateLifecycle.apply(new BondCancelledEvt(cmd.getAggregateId()));
    }

    @Override
    public void created(CreatedCmd cmd) {
        AggregateLifecycle.apply(new BondCreatedEvt(cmd.getAggregateId()));
    }

    @Override
    public void updated(UpdatedCmd cmd) {
        AggregateLifecycle.apply(new BondUpdatedEvt(cmd.getAggregateId()));

    }
}
