package com.abc.logManagement.dto

data class LogCreatedResponse(
    var httpStatus:Int,
    var message:Int,
    var logCreated: LogCreated
)
