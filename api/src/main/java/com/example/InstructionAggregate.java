package com.example;

import com.example.bean.cmd.ApproveCmd;
import com.example.bean.cmd.CancelCmd;
import com.example.bean.cmd.CreateCmd;
import com.example.bean.cmd.UpdateCmd;
import com.example.bean.evt.ApproveEvt;
import com.example.bean.evt.CancelEvt;
import com.example.bean.evt.CreateEvt;
import com.example.bean.evt.UpdateEvt;
import com.example.bean.vo.Instruction;
import com.example.status.ApproveStatus;
import com.example.status.CancelStatus;
import com.example.status.CreateStatus;
import com.example.status.InstructionStatus;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.springframework.beans.BeanUtils;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:11 下午
 **/
@Slf4j
@NoArgsConstructor
public class InstructionAggregate<T extends Instruction> {

    @AggregateIdentifier
    protected String aggregateId;

    protected T instruction;

    protected InstructionStatus<T> status = new CreateStatus<>();


    @CommandHandler
    public InstructionAggregate(CreateCmd<T> cmd) {
        CreateEvt<T> evt = new CreateEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        apply(evt);
    }

    @CommandHandler
    public void handler(UpdateCmd<T> cmd) {
        UpdateEvt<T> evt = new UpdateEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        apply(evt);
    }

    @CommandHandler
    public void handler(CancelCmd<T> cmd) {
        CancelEvt<T> evt = new CancelEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        apply(evt);
    }

    @CommandHandler
    public void handler(ApproveCmd<T> cmd) {
        ApproveEvt<T> evt = new ApproveEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        apply(evt);
    }


    @EventSourcingHandler
    public void on(ApproveEvt<T> evt) {
        this.status = new ApproveStatus<>();
    }


    @EventSourcingHandler
    public void on(CreateEvt<T> evt) {
        this.aggregateId = evt.getAggregateId();
        this.instruction = evt.getInstruction();
        log.info("create {}", evt);
    }

    @EventSourcingHandler
    public void on(CancelEvt<T> evt) {
        this.instruction = evt.getInstruction();
        this.status = new CancelStatus<>();
        log.info("Cancel {}", evt);
    }

    @EventSourcingHandler
    public void on(UpdateEvt<T> evt) {
        this.instruction = evt.getInstruction();
        log.info("update {}", evt);
    }


}
