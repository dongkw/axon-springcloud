package com.example;


import com.example.bean.*;
import com.example.bean.vo.Instruction;
import com.example.status.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:11 下午
 **/
@Slf4j
@NoArgsConstructor
public abstract class InstructionAggregate<T extends Instruction> {

    @AggregateIdentifier
    protected String aggregateId;

    protected T instruction;

    protected InstructionStatus<T> status = new DefaultStatus<>();

    protected InstructionStatus<T> lastStatus;

//    @CommandHandler
//    public InstructionAggregate(CreateCmd<T> cmd) {
//        status.handler(this, cmd);
//    }

    @CommandHandler
    public void handler(CreatedCmd cmd) {
        status.handler(this, cmd);
    }

    @CommandHandler
    public void handler(UpdateCmd<T> cmd) {
        status.handler(this, cmd);
    }

    @CommandHandler
    public void handler(UpdatedCmd cmd) {
        status.handler(this, cmd);
    }

    @CommandHandler
    public void handler(CancelCmd<T> cmd) {
        status.handler(this, cmd);
    }

    @CommandHandler
    public void handler(CancelledCmd cmd) {
        status.handler(this, cmd);
    }

    @CommandHandler
    public void handler(ApproveCmd<T> cmd) {
        status.handler(this, cmd);
    }

    @CommandHandler
    public void handler(DistributeCmd<T> cmd) {
        status.handler(this, cmd);
    }



    @EventSourcingHandler
    public void on(ApproveEvt<T> evt) {
        this.status = new ApproveStatus<>();
        log.info("approve {}", evt);

    }

    @EventSourcingHandler
    public void on(DistributeEvt<T> evt) {
        this.status = new DistributeStatus<>();
        log.info("distribute {}", evt);
    }

    @EventSourcingHandler
    public void on(CreateEvt<T> evt) {
        this.aggregateId = evt.getAggregateId();
        this.instruction = evt.getInstruction();
        this.status = new CreateStatus<>();
        log.info("create {}", evt);
    }

    @EventSourcingHandler
    public void on(CreatedEvt evt) {
        this.status = new CreatedStatus<>();
        log.info("create d{}", evt);
    }

    @EventSourcingHandler
    public void on(CancelEvt<T> evt) {
        this.instruction = evt.getInstruction();
        this.lastStatus = this.status;
        this.status = new CancelStatus<>();
        log.info("Cancel {}", evt);
    }

    @EventSourcingHandler
    public void on(CancelledEvt evt) {
        this.status = new CancelledStatus<>();
        this.lastStatus = null;
        log.info("Cancelled {}", evt);
    }

    @EventSourcingHandler
    public void on(UpdateEvt<T> evt) {
        this.instruction = evt.getInstruction();
        this.lastStatus = this.status;
        this.status = new UpdateStatus<>();
        log.info("update {}", evt);
    }

    @EventSourcingHandler
    public void on(UpdatedEvt evt) {
        log.info("update {}", evt);
        this.status = this.lastStatus;
        this.lastStatus = null;
    }

    public abstract void create(CreateCmd<T> cmd);

    public abstract void update(UpdateCmd<T> cmd);

    public abstract void cancel(CancelCmd<T> cmd);

    public abstract void approve(ApproveCmd<T> cmd);

    public abstract void distribute(DistributeCmd<T> cmd);

    public abstract void cancelled(CancelledCmd cmd);

    public abstract void created(CreatedCmd cmd);

    public abstract void updated(UpdatedCmd cmd);


}
