package com.example.basic.domain.saga;

import com.example.saga.SagaResult;
import lombok.Data;

@Data
public class Result extends SagaResult {
    private String msg;
}
