package com.example.saga;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author dongkw
 * @Date 2021/1/25、4:20 下午
 **/
public abstract class TransactionGroup<T extends SagaResult> implements ITransaction<T> {

    public TransactionGroup(List<ITransaction<T>> transactions) {
        setTransactions(transactions);
    }

    protected List<ITransaction<T>> transactions;

    public void setTransactions(List<ITransaction<T>> transaction) {
        this.transactions = transaction;
    }

    @Override
    public List<Class<?>> getEventRegList() {
        return transactions.stream().flatMap(t -> t.getEventRegList().stream()).collect(Collectors.toList());
    }

    @Override
    public void setResult(T result) {
        transactions.forEach(t -> t.setResult(result));
    }
}
