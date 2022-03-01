package com.example.status;

import com.example.InstructionAggregate;
import com.example.InstructionStatus;
import com.example.bean.ApproveCmd;
import com.example.bean.CancelCmd;
import com.example.bean.UpdateCmd;
import com.example.bean.vo.Instruction;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author dongkw
 * @Date 2021/5/8、1:26 下午
 **/
@Slf4j
public class CreatedStatus<T extends Instruction> extends InstructionStatus<T> {



    public void handler(InstructionAggregate<T> aggregate, UpdateCmd<T> cmd) {
        aggregate.update(cmd);
    }

    public void handler(InstructionAggregate<T> aggregate, CancelCmd<T> cmd) {
        aggregate.cancel(cmd);

    }
    public void handler(InstructionAggregate<T> aggregate, ApproveCmd<T> cmd) {
        aggregate.approve(cmd);

    }


}
