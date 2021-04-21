package com.example.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2021/1/25、1:49 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmplCmd {
    @TargetAggregateIdentifier
    private String id;
}
