package com.example.status;

import com.example.InstructionAggregate;
import com.example.InstructionStatus;
import com.example.bean.*;
import com.example.bean.vo.Instruction;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author dongkw
 * @Date 2021/5/8、1:26 下午
 **/
@Slf4j
public class CreateStatus<T extends Instruction> extends InstructionStatus<T> {


    public void handler(InstructionAggregate<T> aggregate, CreatedCmd cmd) {
        aggregate.created(cmd);
    }

}
