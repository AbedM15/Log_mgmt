package com.abc.logManagement.dto

import com.abc.logManagement.entities.Log

data class FetchedMicroService (

    var microServiceId:Long?,
    var microServiceName:String,
    var microServiceLogs:MutableList<Log>?

    )
