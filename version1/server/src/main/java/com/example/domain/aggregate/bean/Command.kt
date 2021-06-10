package com.example.domain.aggregate.bean.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateCmd(@TargetAggregateIdentifier var id: String? = null, var data: String? = null)

data class UpdateCmd(@TargetAggregateIdentifier val id: String? = null, val data: String? = null)

data class UpdateConfirm(@TargetAggregateIdentifier val id: String? = null, val data: String? = null)

data class CancelCmd(@TargetAggregateIdentifier val id: String? = null)

data class CancelledCmd(@TargetAggregateIdentifier val id: String? = null, val data: String? = null)

data class ConfirmCmd(@TargetAggregateIdentifier val id: String? = null, val data: String? = null)

data class DistributeCmd(@TargetAggregateIdentifier val id: String? = null)

data class FailCmd(@TargetAggregateIdentifier val id: String? = null)

data class AprvPassCmd(@TargetAggregateIdentifier val id: String? = null)

