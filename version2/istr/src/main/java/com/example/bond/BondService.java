package com.example.bond;

import com.example.basic.Factory;
import com.example.basic.IService;
import com.example.basic.domain.saga.Result;
import com.example.basic.model.CreateCmd;
import com.example.basic.model.CreateEvt;
import com.example.pledge.Pledge;
import com.example.basic.Basic;

import java.util.List;
import java.util.UUID;

public class BondService extends Factory implements IService<Bond> {

    public BondService() {
        map.put(Pledge.class.getSimpleName(), this);
    }

    @Override
    public CreateCmd buildCreateCmd(Bond basic) {
        return new CreateCmd(UUID.randomUUID().toString(), basic);
    }
    @Override
    public CreateEvt buildCreateEvt(String aggrId, Bond basic) {
        return new CreateEvt(aggrId, basic);
    }

    @Override
    public Bond getBy(CreateEvt evt) {
        return (Bond) evt.getIstr();
    }

    @Override
    public Bond getBy(Basic basic) {
        return (Bond) basic;
    }

    @Override
    public List<Class<?>> setCmplList() {
        return null;
    }

    @Override
    public void cmplStart(Bond pledge) {

    }

    @Override
    public void cmplRollback(Bond pledge) {

    }

    @Override
    public Result cmplEventHandler(Object event) {
        return null;
    }
}