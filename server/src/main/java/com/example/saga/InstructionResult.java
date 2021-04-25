package com.example.saga;

import com.example.util.SagaResult;
import lombok.Data;

/**
 * @Author dongkw
 * @Date 2021/4/25、2:27 下午
 **/
@Data
public class InstructionResult extends SagaResult {
    private String id;
}
