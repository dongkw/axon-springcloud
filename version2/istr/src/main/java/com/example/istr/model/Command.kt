package com.example.istr.model

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class IssueCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class ApprovalCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class ApprovalFailCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class DistributeCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class DistributeFailCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class ExecuteCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class FailCmd(@TargetAggregateIdentifier val aggrId: String? = null)


