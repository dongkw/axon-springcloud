package com.example.domain.aggregate;

import com.example.InstructionAggregate;
import com.example.bean.cmd.BondCreateCmd;
import com.example.bean.cmd.BondUpdateCmd;
import com.example.bean.cmd.CancelCmd;
import com.example.bean.evt.BondCreateEvt;
import com.example.bean.evt.BondUpdateEvt;
import com.example.bean.evt.CancelEvt;
import com.example.bean.vo.Bond;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:33 下午
 **/
@Aggregate
@NoArgsConstructor
@Slf4j
public class BondAggr extends InstructionAggregate<Bond> {

    @CommandHandler
    public BondAggr(BondCreateCmd cmd) {
        log.info("bond");

//        this.status.create(() -> {
//            BondCreateEvt evt = new BondCreateEvt();
//            BeanUtils.copyProperties(cmd, evt);
//            return evt;
//        });
//
//        this.status.handler(cmd, this, (t, aggr) -> {
//            BondCreateEvt evt = new BondCreateEvt();
//            BeanUtils.copyProperties(t, evt);
//            return evt;
//        });
        this.status.handler(cmd, this, t -> {
            t = (BondCreateEvt) t;
        });
    }

    @CommandHandler
    protected void handler(BondUpdateCmd cmd) {
        log.info("bond");
        this.status.update(() -> {
            BondUpdateEvt evt = new BondUpdateEvt();
            BeanUtils.copyProperties(cmd, evt);
            return evt;
        });
    }

    @CommandHandler
    protected void cancel(CancelCmd<Bond> cmd) {
        log.info("bond");
        this.status.cancel(() -> {
            CancelEvt<Bond> evt = new CancelEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            return evt;
        });
    }
}
