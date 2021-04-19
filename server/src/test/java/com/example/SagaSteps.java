package com.example;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.aggregate.ResultValidator;
import org.axonframework.test.aggregate.TestExecutor;
import org.axonframework.test.saga.ContinuedGivenState;
import org.axonframework.test.saga.FixtureExecutionResult;
import org.axonframework.test.saga.SagaTestFixture;

import java.lang.reflect.ParameterizedType;
import java.time.Duration;

/**
 * @Author dongkw
 * @Date 2021/4/16、10:34 上午
 **/
public class SagaSteps<T> {
    private SagaTestFixture<T> fixture;
    private FixtureExecutionResult result;

    public SagaSteps() {
        this.fixture = new SagaTestFixture<T>((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public FixtureExecutionResult given(String identifier, Object published, int elapseOfSec) {
        result = fixture
                .givenAggregate(identifier)
                .published(published)
                .whenTimeElapses(Duration.ofSeconds(elapseOfSec))
                .expectActiveSagas(1);
        return result;
    }


    public FixtureExecutionResult expectPublishedCommands(Object... commands) {
        result = result.expectDispatchedCommands(commands);
        return result;
    }

    public FixtureExecutionResult expectPublishedEvents(Object... events) {
        result = result.expectPublishedEvents(events);
        return result;
    }


    public FixtureExecutionResult expectedNoCommandDispatched() {
        result = result.expectNoDispatchedCommands();
        return result;
    }

}
