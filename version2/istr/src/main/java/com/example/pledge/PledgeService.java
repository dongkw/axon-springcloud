package com.example.pledge;

import com.example.basic.Factory;
import com.example.basic.IService;
import com.example.basic.domain.saga.Result;
import com.example.basic.model.CreateCmd;
import com.example.basic.model.CreateEvt;
import com.example.basic.Basic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PledgeService extends Factory implements IService<Pledge> {

    public PledgeService() {
        map.put(Pledge.class.getSimpleName(), this);
    }

    @Override
    public CreateCmd buildCreateCmd(Pledge basic) {
        return new CreateCmd(UUID.randomUUID().toString(), basic);
    }

    @Override
    public CreateEvt buildCreateEvt(String aggrId, Pledge basic) {
        return new CreateEvt(aggrId, basic);
    }

    @Override
    public Pledge getBy(CreateEvt evt) {
        return (Pledge) evt.getIstr();
    }

    @Override
    public Pledge getBy(Basic basic) {
        return (Pledge) basic;
    }

    @Override
    public List<Class<?>> setCmplList() {
        return null;
    }

    @Override
    public void cmplStart(Pledge pledge) {

    }

    @Override
    public void cmplRollback(Pledge pledge) {

    }

    @Override
    public Result cmplEventHandler(Object event) {
        return null;
    }


}
