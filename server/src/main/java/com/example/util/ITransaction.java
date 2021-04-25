package com.example.util;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author dongkw
 * @Date 2021/1/25、4:11 下午
 **/
public interface ITransaction<T extends SagaResult> {

    SagaStatus getStatus();

    List<Class<?>> getEventRegList();

    void start();

    void rollback();

    void eventHandler(Object event);

    void setResult(T result);

    default void buildParallel(Consumer< ParallelTransaction<T>> consumer) {
        ParallelTransaction<T> transaction=new ParallelTransaction<>();
        consumer.accept(transaction);
    }
}
