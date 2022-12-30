package com.abc.logManagement.dto

data class AllEngineersResponse (
    var httpStatus:Int,
    var message:String,
    var allEngineers: MutableList<AllEngineers>?
        )
