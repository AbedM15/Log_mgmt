package com.abc.logManagement.dto

data class AllMicroServicesResponse (
    var httpStatus:Int,
    var message:String,
    var microServices:MutableList<AllMicroServices>?
        )
