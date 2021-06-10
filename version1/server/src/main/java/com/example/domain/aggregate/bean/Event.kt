package com.example.domain.aggregate.bean

import com.example.domain.aggregate.bean.command.CreateCmd

data class AprvPassEvent(val id: String? = null)

data class CancelEvent(val id: String? = null, val data: String? = null)

data class CancelledEvent(val id: String? = null, val data: String? = null)

data class ConfirmEvent(val id: String? = null, val data: String? = null)

data class CreateEvent(var id: String? = null, var data: String? = null) {
    constructor(cmd: CreateCmd) : this(cmd.id, cmd.data)

}


data class DistributeEvent(val id: String? = null, val data: String? = null)

data class FailEvent(val id: String? = null, val data: String? = null)

data class UpdateEvent(val id: String? = null, val data: String? = null)
