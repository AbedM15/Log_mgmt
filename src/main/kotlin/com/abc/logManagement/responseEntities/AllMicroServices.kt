package com.abc.logManagement.responseEntities

import com.abc.logManagement.entities.Microservice

data class AllMicroServices(
    var httpStatus: Int,
    var message: List<Microservice>

)
