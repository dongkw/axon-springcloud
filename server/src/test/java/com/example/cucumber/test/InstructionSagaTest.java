package com.example.cucumber.test;

import com.example.aggregate.bean.command.ConfirmCmd;
import com.example.aggregate.bean.event.CancelEvent;
import com.example.aggregate.bean.event.CreateEvent;
import com.example.command.CmplCmd;
import com.example.command.VerfCmd;
import com.example.event.CmplSuccEvt;
import com.example.saga.CreateSaga;
import com.example.saga.cancel.CancelSaga;
import com.example.util.CmdGatewayFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.test.saga.ContinuedGivenState;
import org.axonframework.test.saga.FixtureExecutionResult;
import org.axonframework.test.saga.SagaTestFixture;

import java.util.Map;

/**
 * @Author dongkw
 * @Date 2021/4/16、10:13 上午
 **/
public class InstructionSagaTest {

    private static SagaTestFixture<CreateSaga> fixture;
    private static SagaTestFixture<CancelSaga> cancelTestFixture;
    private FixtureExecutionResult result;
    private ContinuedGivenState givenState;

//    public void testHandle_TransactionCancelled() throws Exception {
//        fixture.givenAggregate("1").published(new CreateEvent())
//                .whenAggregate("1").publishes(new CreateCmd())
//                .expectActiveSagas(0)
//                .expectDispatchedCommandsMatching(exactSequenceOf(new CmplMatcher("1")));
//    }

    public InstructionSagaTest() {
        fixture = new SagaTestFixture<>(CreateSaga.class);
        cancelTestFixture = new SagaTestFixture<>(CancelSaga.class);
        CmdGatewayFactory.mockCmdGateway(DefaultCommandGateway.builder().commandBus(cancelTestFixture.getCommandBus()).build());
    }

    @Given("^sagaId:$")
    public void sagaid(Map<String, String> map) throws Exception {
        givenState = fixture.givenAggregate(map.get("id")).published();
    }

    @When("^收CreateEvent:$")
    public void 收createevent(Map<String, String> map) throws Exception {
        CreateEvent event = new CreateEvent();
        event.setId(map.get("id"));
        event.setData(map.get("data"));
        result = givenState.whenPublishingA(event)
                .expectActiveSagas(1);
    }

    @Then("^发送取消调用外部:$")
    public void 发送取消调用外部(Map<String, String> map) throws Exception {
        CmplCmd cmplCmd = new CmplCmd();
        cmplCmd.setId(map.get("id"));

        result.expectDispatchedCommands(cmplCmd);
    }

    @Given("^收到CreateEvent:$")
    public void 收到createevent(Map<String, String> map) throws Exception {
        CreateEvent event = new CreateEvent();
        event.setId(map.get("id"));
        event.setData(map.get("data"));
        givenState = fixture.givenAggregate(map.get("id")).published(event);
    }

    @When("^收到外部请求CmplSuccEvent:$")
    public void 收到外部请求cmplsuccevent(Map<String, String> map) throws Exception {
        CmplSuccEvt evt = new CmplSuccEvt();
        evt.setId(map.get("id"));
        result = givenState.whenPublishingA(evt).expectActiveSagas(0);
    }

    @Then("^发送确认命令:$")
    public void 发送确认命令(Map<String, String> map) throws Exception {
        ConfirmCmd cmplCmd = new ConfirmCmd();
        cmplCmd.setId(map.get("id"));

        result.expectDispatchedCommands(cmplCmd);
    }


    @Given("^取消sagaId:$")
    public void 取消sagaid(Map<String, String> map) throws Exception {
        givenState = cancelTestFixture.givenAggregate(map.get("id")).published();

    }

    @When("^收CancelEvent:$")
    public void 收cancelevent(Map<String, String> map) throws Exception {
        CancelEvent event = new CancelEvent();
        event.setId(map.get("id"));
        event.setData(map.get("data"));
        result = givenState.whenPublishingA(event)
                .expectActiveSagas(1);
    }

    @Then("^发送取消调用外部命令:$")
    public void 发送取消调用外部命令(Map<String, String> map) throws Exception {
        CmplCmd cmplCmd = new CmplCmd();
        cmplCmd.setId(map.get("id"));
        VerfCmd verfCmd = new VerfCmd();
        verfCmd.setId(map.get("id"));
        result.expectDispatchedCommands(cmplCmd, verfCmd);
    }
}
