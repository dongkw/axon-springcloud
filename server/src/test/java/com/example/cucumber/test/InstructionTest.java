package com.example.cucumber.test;

import com.example.domain.aggregate.InstructionAggr;
import com.example.domain.aggregate.bean.command.CancelledCmd;
import com.example.domain.aggregate.bean.command.CreateCmd;
import com.example.domain.aggregate.bean.event.CancelledEvent;
import com.example.domain.aggregate.bean.event.CreateEvent;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

/**
 * @Author dongkw
 * @Date 2021/4/16、10:13 上午
 **/
public class InstructionTest extends AggrSteps<InstructionAggr> {


    public InstructionTest() {
    }



    @When("^收到命令:$")
    public void 收到命令(Map<String, String> map) throws Exception {
        CreateCmd cmd = new CreateCmd();
        cmd.setId(map.get("id"));
        cmd.setData(map.get("data"));
        when(cmd);

    }

    @Then("^发布事件:$")
    public void 发布事件(Map<String, String> map) throws Exception {
        CreateEvent event = new CreateEvent();
        event.setId(map.get("id"));
        event.setData(map.get("data"));
        expectEvents(event);
    }

    @Given("^创建以后:$")
    public void 创建以后(List<Map<String, String>> list) throws Exception {
        list.forEach(map -> {
            CreateEvent event = new CreateEvent();
            event.setId(map.get("id"));
            event.setData(map.get("data"));
            given(event);
        });
    }

    @When("^取消$")
    public void 取消(List<Map<String, String>> list) throws Exception {
        list.forEach(map -> {
            CancelledCmd cmd = new CancelledCmd();
            cmd.setId(map.get("id"));
            cmd.setData(map.get("data"));
            when(cmd);
        });
    }

    @Then("^取消后$")
    public void 取消后(List<Map<String, String>> list) throws Exception {
        list.forEach(map -> {
            CancelledEvent event = new CancelledEvent();
            event.setId(map.get("id"));
            event.setData(map.get("data"));
            expectEvents(event);
        });
    }
}
