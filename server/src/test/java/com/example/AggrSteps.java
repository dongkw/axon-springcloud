package com.example;

import com.example.aggregate.InstructionAggr;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.aggregate.ResultValidator;
import org.axonframework.test.aggregate.TestExecutor;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Author dongkw
 * @Date 2021/4/16、10:34 上午
 **/
public class AggrSteps<T> {
    protected FixtureConfiguration<T> fixture;
    private ResultValidator<T> validator;
    private TestExecutor<T> testExecutor;


    public AggrSteps() {
        this.fixture = new AggregateTestFixture<T>((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        testExecutor = fixture.given();
    }


    public TestExecutor<T> given(Object... var1) {

        testExecutor = fixture.given(var1);
        return testExecutor;
    }

    public ResultValidator<T> when(Object whenDefined) {
        validator = testExecutor.when(whenDefined).expectSuccessfulHandlerExecution();
        return validator;
    }

    public ResultValidator<T> expectEvents(Object... event) {
        validator = validator.expectEvents(event);
        return validator;
    }


    public ResultValidator<T> expectNoEvents() {
        validator = validator.expectNoEvents();
        return validator;
    }


}
