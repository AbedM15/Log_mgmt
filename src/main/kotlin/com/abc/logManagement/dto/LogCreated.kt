package com.abc.logManagement.dto

import com.abc.logManagement.entities.MicroService
import java.time.LocalDateTime

data class LogCreated (

    var httpStatus:Int,
    var message:String


    )
