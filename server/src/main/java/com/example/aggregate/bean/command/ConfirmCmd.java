package com.example.aggregate.bean.command;

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
public class ConfirmCmd {
    @TargetAggregateIdentifier
    private String id;
    private String data;
}
