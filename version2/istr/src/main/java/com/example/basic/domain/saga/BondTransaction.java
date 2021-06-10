package com.example.basic.domain.saga;

import com.example.saga.TransactionUnit;

import java.util.List;

public class BondTransaction extends TransactionUnit<Result> {

    @Override
    public List<Class<?>> getEventRegList() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void eventHandler(Object event) {

    }

    @Override
    public void setResult(Result result) {

    }
}
