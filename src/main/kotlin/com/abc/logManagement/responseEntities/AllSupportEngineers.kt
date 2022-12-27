package com.abc.logManagement.responseEntities

import com.abc.logManagement.entities.SupportEngineer

data class AllSupportEngineers (
    var httpStatus:Int,
    var message:String,
    var allSupportEngineers:List<SupportEngineer>?
)
