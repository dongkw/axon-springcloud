package com.example;

import com.example.bean.*;
import com.example.bean.vo.Instruction;

/**
 * @Author dongkw
 * @Date 2021/5/8、1:26 下午
 **/
public class InstructionStatus<T extends Instruction> {


    public void handler(InstructionAggregate<T> aggregate, CreateCmd<T> cmd) {
    }

    public void handler(InstructionAggregate<T> aggregate, UpdateCmd<T> cmd) {
    }

    public void handler(InstructionAggregate<T> aggregate, CancelCmd<T> cmd) {
    }

    public void handler(InstructionAggregate<T> aggregate, ApproveCmd<T> cmd) {
    }

    public void handler(InstructionAggregate<T> aggregate, DistributeCmd<T> cmd) {
    }

    public void handler(InstructionAggregate<T> aggregate, CreatedCmd cmd) {
    }

    public void handler(InstructionAggregate<T> aggregate, UpdatedCmd cmd) {
    }

    public void handler(InstructionAggregate<T> aggregate, CancelledCmd cmd) {
    }


}
