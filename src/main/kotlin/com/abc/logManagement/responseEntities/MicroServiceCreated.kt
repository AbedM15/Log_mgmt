package com.abc.logManagement.responseEntities

import com.abc.logManagement.entities.Microservice

data class MicroServiceCreated(
    var httpStatus: Int,
    var message:String,
    var microServiceCreated: Microservice
)
