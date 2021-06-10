package com.example.bean

import com.example.bean.vo.Bond
import com.example.bean.vo.Instruction
import com.example.bean.vo.Pledge

open class InstructionEvent<T : Instruction?> {
    var aggregateId: String? = null
    open var instruction: T? = null

    init {
        aggregateId = instruction?.instructionID;
    }
}


open class ApproveEvt<T : Instruction?> : InstructionEvent<T>()
open class CreateEvt<T : Instruction?> : InstructionEvent<T>()
open class CancelEvt<T : Instruction?> : InstructionEvent<T>()
open class DistributeEvt<T : Instruction?> : InstructionEvent<T>()
open class UpdateEvt<T : Instruction?> : InstructionEvent<T>()
open class CancelledEvt(open val aggregateId: String? = null)
open class CreatedEvt(open val aggregateId: String? = null)
open class UpdatedEvt(open val aggregateId: String? = null)


data class BondCreateEvt(override var instruction: Bond?) : CreateEvt<Bond?>()
data class BondUpdateEvt(override var instruction: Bond?) : UpdateEvt<Bond?>()
data class BondCancelEvt(override var instruction: Bond?) : CancelEvt<Bond?>()
data class BondApproveEvt(override var instruction: Bond?) : ApproveEvt<Bond?>()
data class BondDistributeEvt(override var instruction: Bond?) : DistributeEvt<Bond?>()
data class BondCreatedEvt(override val aggregateId: String?) : CreatedEvt();
data class BondCancelledEvt(override val aggregateId: String?) : CancelledEvt();
data class BondUpdatedEvt(override val aggregateId: String?) : UpdatedEvt();



class PrCreateEvt(override var instruction: Pledge?) : CreateEvt<Pledge?>()
class PrUpdateEvt(override var instruction: Pledge?) : UpdateEvt<Pledge?>()

