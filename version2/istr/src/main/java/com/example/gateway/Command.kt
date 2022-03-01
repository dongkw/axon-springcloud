package com.example.gateway

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CmplCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class CmplRollbackCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class BondCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class BondRollbackCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class BookCmd(@TargetAggregateIdentifier val aggrId: String? = null)
data class BookRollbackCmd(@TargetAggregateIdentifier val aggrId: String? = null)