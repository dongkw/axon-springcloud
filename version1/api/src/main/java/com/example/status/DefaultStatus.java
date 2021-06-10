package com.example.status;

import com.example.InstructionAggregate;
import com.example.InstructionStatus;
import com.example.bean.CreateCmd;
import com.example.bean.vo.Instruction;

/**
 * @Author dongkw
 * @Date 2021/5/31、3:12 下午
 **/
public class DefaultStatus<T extends Instruction> extends InstructionStatus<T> {

    public void handler(InstructionAggregate<T> aggregate, CreateCmd<T> cmd){
        aggregate.create(cmd);
    }
}
