package com.abc.logManagement.dto

import com.abc.logManagement.entities.Log

data class LogsByValueResponse (
    var httpStatus: Int,
    var message:String,
    var logs:MutableList<SearchByLogsResponse>?



        )