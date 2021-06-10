package com.example.basic.domain.saga;

import com.example.basic.model.CreateConfirmCmd;
import com.example.basic.model.CreateEvt;
import com.example.basic.model.CreateFailCmd;
import com.example.saga.*;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.Arrays;
import java.util.Objects;

@Saga
public class CreateSaga {

    private ITransaction<Result> transaction;

    private String aggrId;

    @StartSaga
    @SagaEventHandler(associationProperty = "aggrId")
    public void startSaga(CreateEvt evt) {
        aggrId = evt.getAggrId();
        transaction = new ParallelTransaction<>(Arrays.asList(
        new BondTransaction(), new BondTransaction(), new CmplTransaction(evt.getIstr())));
        transaction.start();
    }


    private void end(Object evt) {
        Result result = new Result();
        transaction.eventHandler(evt);
        if (Objects.equals(transaction.getStatus(), SagaStatus.SUCCESS)) {
            transaction.setResult(result);
            CreateConfirmCmd cmd = new CreateConfirmCmd(aggrId);
            CmdGatewayFactory.getCommandGateway().send(cmd);
            SagaLifecycle.end();
        } else if (Objects.equals(transaction.getStatus(), SagaStatus.FAIL)) {
            CreateFailCmd cmd = new CreateFailCmd(aggrId);
            CmdGatewayFactory.getCommandGateway().send(cmd);
            SagaLifecycle.end();
        }


    }

}