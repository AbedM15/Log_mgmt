package com.abc.logManagement.dto

data class SupportEngineerCreatedResponse (
    var httpStatus:Int,
    var message:String,
    var supportEngineerCreated: SupportEngineerCreated
    )
