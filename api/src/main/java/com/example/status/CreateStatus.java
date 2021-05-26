package com.example.status;

import com.example.InstructionAggregate;
import com.example.bean.cmd.CreateCmd;
import com.example.bean.evt.CreateEvt;
import com.example.bean.evt.InstructionEvent;
import com.example.bean.vo.Instruction;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.beans.BeanUtils;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author dongkw
 * @Date 2021/5/8、1:26 下午
 **/
@Slf4j
public class CreateStatus<T extends Instruction> extends InstructionStatus<T> {

    public void create(Supplier<InstructionEvent<T>> supplier) {
        AggregateLifecycle.apply(supplier.get());
    }

    public void create(CreateCmd<T> cmd, Function<CreateCmd<T>, InstructionEvent<T>> function) {
        AggregateLifecycle.apply(function.apply(cmd));
    }


    public void handler(CreateCmd<T> cmd, InstructionAggregate<T> aggregate,
                        BiFunction<CreateCmd<T>, InstructionAggregate<T>, InstructionEvent<T>> function) {
        AggregateLifecycle.apply(function.apply(cmd, aggregate));
    }

    public void handler(CreateCmd<T> cmd, InstructionAggregate<T> aggregate,
                        Consumer<InstructionEvent<T>> function) {
        CreateEvt<T> evt = new CreateEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        function.accept(evt);
        AggregateLifecycle.apply(evt);
    }

    public void update(Supplier<InstructionEvent<T>> supplier) {
        log.info("send evt {}", supplier.get());
        AggregateLifecycle.apply(supplier.get());
    }

    public void approve(Supplier<InstructionEvent<T>> supplier) {
        log.info("send evt {}", supplier.get());
        AggregateLifecycle.apply(supplier.get());

    }
}
