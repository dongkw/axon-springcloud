package com.example.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2021/1/25、3:37 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerfCmd {
    @TargetAggregateIdentifier
    private String id;
}
