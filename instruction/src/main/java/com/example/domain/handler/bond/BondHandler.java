package com.example.domain.handler.bond;

import com.example.bean.evt.BondCreateEvt;
import com.example.bean.evt.BondUpdateEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2021/5/7、5:13 下午
 **/
@Slf4j
@Component
public class BondHandler {


    @EventHandler
    public void handlerBond(BondCreateEvt evt) {

//        String s = evt.getInstruction().getBondName();
//        log.info("{}", s);
        log.info("save Bond {}", evt);
    }

    @EventHandler
    public void handlerBond(BondUpdateEvt evt) {

//        String s = evt.getInstruction().getBondName();
//        log.info("{}", s);
        log.info("update Bond {}", evt);
    }
}
