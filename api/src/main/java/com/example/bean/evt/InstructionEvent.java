package com.example.bean.evt;

import com.example.bean.vo.Instruction;
import lombok.Data;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:21 下午
 **/
@Data
public class InstructionEvent<T extends Instruction> {
    private String aggregateId;
    private T instruction;
}
