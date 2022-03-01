package com.example.bean

import com.example.bean.vo.Bond
import com.example.bean.vo.Instruction
import com.example.bean.vo.Pledge
import org.axonframework.modelling.command.TargetAggregateIdentifier

open class InstructionCommand<T : Instruction?> {
    @TargetAggregateIdentifier
    var aggregateId: String? = null
    open var instruction: T? = null

    init {
        aggregateId = instruction?.instructionID
    }
}

open class CreateCmd<T : Instruction?> : InstructionCommand<T>()
open class UpdateCmd<T : Instruction?> : InstructionCommand<T>()
open class CancelCmd<T : Instruction?> : InstructionCommand<T>()
open class ApproveCmd<T : Instruction?> : InstructionCommand<T>()
open class DistributeCmd<T : Instruction?> : InstructionCommand<T>()

data class CancelledCmd(@TargetAggregateIdentifier val aggregateId: String? = null)
data class CreatedCmd(@TargetAggregateIdentifier val aggregateId: String? = null)
data class UpdatedCmd(@TargetAggregateIdentifier val aggregateId: String? = null)


data class BondCreateCmd(override var instruction: Bond?) : CreateCmd<Bond>()
data class BondUpdateCmd(override var instruction: Bond?) : UpdateCmd<Bond>()


data class PrCreateCmd(override var instruction: Pledge?) : CreateCmd<Pledge>()

