package com.example.util;

/**
 * @Author dongkw
 * @Date 2021/1/25、4:20 下午
 **/
public abstract class TransactionUnit<T extends SagaResult> implements ITransaction<T> {

    protected SagaStatus sagaStatus;

    public TransactionUnit() {
        getEventRegList();
    }

    @Override
    public SagaStatus getStatus() {
        return sagaStatus;
    }
}
