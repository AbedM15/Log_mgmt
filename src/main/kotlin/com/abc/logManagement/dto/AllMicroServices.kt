package com.abc.logManagement.dto

import com.abc.logManagement.entities.Log


data class AllMicroServices(

    var microServiceId:Long? = 1,
    var microServiceName:String,
    var microServiceLogs:MutableList<Log>?

    )
