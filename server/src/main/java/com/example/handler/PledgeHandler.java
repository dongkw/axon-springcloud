package com.example.handler;

import com.example.bean.CreateEvt;
import com.example.bean.vo.Pledge;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2021/5/7、5:13 下午
 **/
@Slf4j
@Component
public class PledgeHandler {

    @EventHandler
    public void handlerPledge(CreateEvt<Pledge> evt) {
//        String s = evt.getInstruction().getTerm();
//        log.info("{}", s);
        log.info("save Pledge {}", evt);
    }

}
