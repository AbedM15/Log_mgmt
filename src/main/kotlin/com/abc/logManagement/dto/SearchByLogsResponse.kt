package com.abc.logManagement.dto

import java.time.LocalDateTime

data class SearchByLogsResponse (
    val logId:Long?,
    val level:String,
    val resolution:String?,
    val logMessage:String,
    val time:LocalDateTime,
    val microServiceId:Long?,
    val microServiceName:String?
    )