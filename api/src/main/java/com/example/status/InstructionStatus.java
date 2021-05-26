package com.example.status;

import com.example.InstructionAggregate;
import com.example.bean.cmd.CreateCmd;
import com.example.bean.evt.*;
import com.example.bean.vo.Instruction;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author dongkw
 * @Date 2021/5/8、1:26 下午
 **/
public class InstructionStatus<T extends Instruction> {

    public void create(Supplier<InstructionEvent<T>> supplier) {

    }

    public void update(Supplier<InstructionEvent<T>> supplier) {

    }

    public void approve(Supplier<InstructionEvent<T>> supplier) {

    }

    public void cancel(Supplier<InstructionEvent<T>> supplier) {

    }

    public void distribute(Supplier<InstructionEvent<T>> supplier) {

    }

    public void create(CreateCmd<T> cmd, Function<CreateCmd<T>, InstructionEvent<T>> function) {
    }

    public void handler(CreateCmd<T> cmd, InstructionAggregate<T> aggregate,
                        BiFunction<CreateCmd<T>, InstructionAggregate<T>, InstructionEvent<T>> function) {
    }

    public void handler(CreateCmd<T> cmd, InstructionAggregate<T> aggregate, Consumer<InstructionEvent<T>> consumer) {
    }
}
