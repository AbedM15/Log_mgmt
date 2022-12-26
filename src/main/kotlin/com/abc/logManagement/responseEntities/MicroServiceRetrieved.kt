package com.abc.logManagement.responseEntities

import com.abc.logManagement.entities.Microservice

data class MicroServiceRetrieved (
    var httpStatus: Int,
    var microService:Microservice?
)
