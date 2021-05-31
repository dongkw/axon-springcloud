package com.example.domain.aggregate;

import com.example.InstructionAggregate;
import com.example.bean.*;
import com.example.bean.vo.Pledge;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:33 下午
 **/
@Aggregate
@NoArgsConstructor
@Slf4j
public class PledgeAggr extends InstructionAggregate<Pledge> {

    @CommandHandler
    public PledgeAggr(PrCreateCmd cmd) {
        this.status.handler(this, cmd);
    }


    @Override
    public void create(CreateCmd<Pledge> cmd) {

    }

    @Override
    public void update(UpdateCmd<Pledge> cmd) {

    }

    @Override
    public void cancel(CancelCmd<Pledge> cmd) {

    }

    @Override
    public void approve(ApproveCmd<Pledge> cmd) {

    }

    @Override
    public void distribute(DistributeCmd<Pledge> cmd) {

    }

    @Override
    public void cancelled(CancelledCmd cmd) {

    }

    @Override
    public void created(CreatedCmd cmd) {

    }

    @Override
    public void updated(UpdatedCmd cmd) {

    }
}
