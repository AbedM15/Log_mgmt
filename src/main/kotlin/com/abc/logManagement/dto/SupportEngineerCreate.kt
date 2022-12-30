package com.abc.logManagement.dto

import com.abc.logManagement.entities.MicroService


data class SupportEngineerCreate(
    var firstName:String,
    var lastName:String,
    var emailAddress:String,
    var microServices:MutableSet<MicroService>?
    )
