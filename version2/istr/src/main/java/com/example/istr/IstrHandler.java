package com.example.istr;


import com.example.istr.model.ApprovalCmd;
import com.example.istr.model.FailCmd;
import com.example.istr.model.IssueCmd;
import com.example.basic.model.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IstrHandler {

    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void handler(CreateEvt evt) {
        commandGateway.send(new IssueCmd(evt.getIstr().getInstructionId()));
    }

    @EventHandler
    public void handler(CreateConfirmEvt evt) {
        commandGateway.send(new ApprovalCmd(evt.getIstr().getInstructionId()));
    }

    @EventHandler
    public void handler(UpdateConfirmEvt evt) {
        commandGateway.send(new ApprovalCmd(evt.getIstr().getInstructionId()));
    }

    @EventHandler
    public void handler(CancelConfirmEvt evt) {
        commandGateway.send(new FailCmd(evt.getIstr().getInstructionId()));
    }

    @EventHandler
    public void handler(CreateFailEvt evt) {
        commandGateway.send(new FailCmd(evt.getIstr().getInstructionId()));
    }
}
