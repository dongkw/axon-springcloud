package com.example.basic.model

import com.example.basic.Basic
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateEvt(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic? = null)
data class CreateConfirmEvt(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic? = null)
data class CreateFailEvt(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic? = null)

data class UpdateEvt(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic? = null)
data class UpdateConfirmEvt(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic? = null)
data class UpdateFailEvt(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic? = null)

data class CancelEvt(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic? = null)
data class CancelConfirmEvt(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic? = null)
data class CancelFailEvt(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic? = null)
