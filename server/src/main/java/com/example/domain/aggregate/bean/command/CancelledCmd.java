package com.example.domain.aggregate.bean.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2021/1/25、1:28 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelledCmd {
    @TargetAggregateIdentifier
    private String id;
    private String data;
}
