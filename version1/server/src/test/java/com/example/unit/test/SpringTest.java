//package com.example.unit.test;
//
//import com.example.domain.aggregate.InstructionAggr;
//import com.example.domain.aggregate.bean.command.CancelledCmd;
//import com.example.domain.aggregate.bean.command.ConfirmCmd;
//import com.example.domain.aggregate.bean.command.CreateCmd;
//import com.example.domain.aggregate.bean.event.CancelEvent;
//import com.example.domain.aggregate.bean.event.CancelledEvent;
//import com.example.domain.aggregate.bean.event.CreateEvent;
//import com.example.command.CmplCmd;
//import com.example.command.VerfCmd;
//import com.example.event.CmplSuccEvt;
//import com.example.event.VerfSuccEvt;
//import com.example.saga.CreateSaga;
//import com.example.saga.cancel.CancelSaga;
//import com.example.util.CmdGatewayFactory;
//import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
//import org.axonframework.test.aggregate.AggregateTestFixture;
//import org.axonframework.test.aggregate.FixtureConfiguration;
//import org.axonframework.test.saga.SagaTestFixture;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * @ClassName PledgeDistributeControllerTest
// * @Description 分发请求测试
// * @Author songxubei
// * @Date 2021/4/7 11:09
// **/
////@RunWith(SpringRunner.class)
//public class SpringTest {
//
//    SagaTestFixture<CreateSaga> sagaTestFixture;
//    SagaTestFixture<CancelSaga> cancelSaga;
//
//    FixtureConfiguration<InstructionAggr> fixture;
//
//    @Before
//    public void set() {
//        sagaTestFixture = new SagaTestFixture<>(CreateSaga.class);
//        cancelSaga = new SagaTestFixture<>(CancelSaga.class);
//        fixture = new AggregateTestFixture<>(InstructionAggr.class);
//        CmdGatewayFactory.mockCmdGateway(DefaultCommandGateway.builder().commandBus(cancelSaga.getCommandBus()).build());
//
//    }
//
//    @Test
//    public void testCreate() {
//        fixture.given().when(new CreateCmd("1", "2"))
//                .expectSuccessfulHandlerExecution()
//                .expectEvents(new CreateEvent("1", "2"));
//    }
//
//    @Test
//    public void testCancel() {
//        fixture.given(new CreateEvent("1", "2"))
//                .when(new CancelledCmd("1", "2"))
//                .expectSuccessfulHandlerExecution()
//                .expectEvents(new CancelledEvent("1", "2"));
//    }
//
//
//    @Test
//    public void testSagaCancel() {
//
//        cancelSaga.givenAggregate("1")
//                .published()
//                .whenPublishingA(new CancelEvent("1", "2"))
//                .expectActiveSagas(1)
//                .expectDispatchedCommands(new CmplCmd("1"), new VerfCmd("1"));
//
//    }
//
//    @Test
//    public void testSagaCancelSucc() {
//
//        cancelSaga.givenAggregate("1")
//                .published(new CancelEvent("1", "2"))
//                .andThenAPublished(new CmplSuccEvt("1"))
//                .whenPublishingA(new VerfSuccEvt("1"))
//                .expectActiveSagas(0)
//                .expectDispatchedCommands(new CancelledCmd("1", null));
//
//    }
//
//    @Test
//    public void teatSagaCreate() {
//        sagaTestFixture.givenAggregate("1")
//                .published()
//                .whenPublishingA(new CreateEvent("1", "2"))
//                .expectActiveSagas(1)
//                .expectDispatchedCommands(new CmplCmd("1"));
//    }
//
//    @Test
//    public void teatSagaCreateSucc() {
//        sagaTestFixture.givenAggregate("1")
//                .published(new CreateEvent("1", "2"))
//                .whenPublishingA(new CmplSuccEvt("1"))
//                .expectActiveSagas(0)
//                .expectDispatchedCommands(new ConfirmCmd("1", null));
//    }
//
//}