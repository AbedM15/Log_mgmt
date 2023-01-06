package com.abc.logManagement.dto

import com.abc.logManagement.entities.MicroService
import java.time.LocalDateTime

data class CreateLogEntry(

    var level:String,
    var log:String,
    var time: String,
    var microServiceId: Long

)
