package com.abc.logManagement.dto

import com.abc.logManagement.entities.Log
import com.abc.logManagement.entities.SupportEngineer

data class FetchedMicroService (

    var microServiceId:Long?,
    var microServiceName:String?,
    var microServiceLogs:MutableList<Log>?,
    var supportEngineers:MutableSet<MicroServiceByIdSupportEngineer>?

    )
