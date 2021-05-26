package com.example.domain.aggregate;

import com.example.InstructionAggregate;
import com.example.bean.cmd.PrCreateCmd;
import com.example.bean.cmd.UpdateCmd;
import com.example.bean.evt.PrCreateEvt;
import com.example.bean.evt.PrUpdateEvt;
import com.example.bean.vo.Pledge;
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
public class PledgeAggr extends InstructionAggregate<Pledge> {

    @CommandHandler
    public PledgeAggr(PrCreateCmd cmd) {
        log.info("pledge");
        this.status.create(() -> {
            PrCreateEvt evt = new PrCreateEvt();
            BeanUtils.copyProperties(cmd, evt);
            return evt;
        });
    }

    @CommandHandler
    protected void update(UpdateCmd<Pledge> cmd) {
        log.info("pledge");
        this.status.update(() -> {
            PrUpdateEvt evt = new PrUpdateEvt();
            BeanUtils.copyProperties(cmd, evt);
            return evt;
        });
    }

}
