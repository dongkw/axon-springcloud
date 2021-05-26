package com.example.status;

import com.example.bean.evt.InstructionEvent;
import com.example.bean.vo.Instruction;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.function.Supplier;

/**
 * @Author dongkw
 * @Date 2021/5/8、1:26 下午
 **/
@Slf4j
public class ApproveStatus<T extends Instruction> extends InstructionStatus<T> {

    public void distribute(Supplier<InstructionEvent<T>> supplier) {
        log.info("send evt {}", supplier.get());
        AggregateLifecycle.apply(supplier.get());
    }
}
