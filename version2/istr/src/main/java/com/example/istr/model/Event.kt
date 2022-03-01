package com.example.istr.model

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class IssueEvt(val aggrId: String? = null)

data class ApprovalEvt(val aggrId: String? = null)
data class ApprovalFailEvt(val aggrId: String? = null)

data class DistributeEvt(val aggrId: String? = null)
data class DistributeFailEvt(val aggrId: String? = null)

data class ExecuteEvt(val aggrId: String? = null)

data class FailEvt(val aggrId: String? = null)


