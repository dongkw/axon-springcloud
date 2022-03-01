package com.example.saga;

import com.example.command.CmplCmd;
import com.example.domain.aggregate.bean.CreateEvent;
import com.example.domain.aggregate.bean.command.ConfirmCmd;
import com.example.domain.aggregate.bean.command.FailCmd;
import com.example.event.CmplFailEvt;
import com.example.event.CmplSuccEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author dongkw
 * @Date 2021/1/25、11:15 上午
 **/
@Saga
@Slf4j
@ProcessingGroup(value = "saga-create")
public class CreateSaga {

    private CreateEvent createEvent;

    private transient CommandGateway commandGateway;

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void startSaga(CreateEvent event) {

        this.createEvent = event;
        CmplCmd cmd = new CmplCmd();
        cmd.setId(event.getId());
        commandGateway.send(cmd);
        log.info("send {}", cmd);

    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(CmplFailEvt evt) {
        log.info("saga fail");
        FailCmd cmd = new FailCmd(this.createEvent.getId());
        commandGateway.send(cmd);
    }


    @SagaEventHandler(associationProperty = "id")
    public void handler(CmplSuccEvt evt) {
        log.info("saga success");
        ConfirmCmd cmd = new ConfirmCmd(evt.getId(),this.createEvent.getData());
        commandGateway.send(cmd);
        SagaLifecycle.end();
    }

//    @SagaEventHandler(associationProperty = "id")
//    public void handler(CmplRollbackEvt evt) {
//        FailCmd cmd = new FailCmd();
//        cmd.setId(this.createEvent.getId());
//        commandGateway.send(cmd);
//
//    }
}
