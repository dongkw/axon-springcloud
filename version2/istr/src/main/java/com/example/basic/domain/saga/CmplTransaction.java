package com.example.basic.domain.saga;

import com.example.basic.Factory;
import com.example.basic.IService;
import com.example.basic.Basic;
import com.example.saga.TransactionUnit;

import java.util.List;

public class CmplTransaction extends TransactionUnit<Result> {

    IService factory;

    Basic basic;

    Result result;

    public CmplTransaction(Basic basic) {
        factory = Factory.getFactory(basic.getClass().getSimpleName());
    }

    @Override
    public List<Class<?>> getEventRegList() {
        return factory.setCmplList();
    }

    @Override
    public void start() {
        factory.cmplStart(basic);
    }

    @Override
    public void rollback() {
        factory.cmplRollback(basic);
    }

    @Override
    public void eventHandler(Object event) {
        result = factory.cmplEventHandler(event);
    }

    @Override
    public void setResult(Result result) {
        result = this.result;
    }
}
