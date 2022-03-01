package com.example.basic.model

import com.example.basic.Basic
import com.example.pledge.Pledge
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateCmd(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Basic)
data class CreateConfirmCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class CreateFailCmd(@TargetAggregateIdentifier val aggrId: String? = null)

data class UpdateCmd(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Pledge)
data class UpdateConfirmCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class UpdateFailCmd(@TargetAggregateIdentifier val aggrId: String? = null, var istr: Pledge)

data class CancelCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class CancelConfirmCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class CancelFailCmd(@TargetAggregateIdentifier val aggrId: String? = null)

