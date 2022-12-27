package com.abc.logManagement.responseEntities

import com.abc.logManagement.entities.SupportEngineer

data class SupportEngineerRetrieved(
    var httpStatus:Int,
    var message:String,
    var supportEngineer:SupportEngineer
)
