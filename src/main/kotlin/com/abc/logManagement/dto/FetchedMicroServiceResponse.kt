package com.abc.logManagement.dto


data class FetchedMicroServiceResponse (

    var httpStatus: Int,
    var message:String,
    var microService: FetchedMicroService
        )
