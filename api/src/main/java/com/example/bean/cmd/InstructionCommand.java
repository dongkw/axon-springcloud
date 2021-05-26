package com.example.bean.cmd;

import com.example.bean.vo.Instruction;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:21 下午
 **/
@Data
public class InstructionCommand<T extends Instruction> {
    @TargetAggregateIdentifier
    private String aggregateId;
    private T instruction;
}
