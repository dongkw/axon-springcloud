package com.example.domain.aggregate.bean.command;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
public class AprvPassCmd {


    @TargetAggregateIdentifier
    private String id;
}
