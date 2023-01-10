package com.abc.logManagement.services

import com.abc.logManagement.dto.CreateLogEntry
import com.abc.logManagement.dto.LogCreated
import com.abc.logManagement.dto.SearchByLogsResponse
import com.abc.logManagement.entities.Log

interface LogService {
     fun createLogEntry(log: CreateLogEntry): String

     fun getLogsByValue(value: String): MutableList<SearchByLogsResponse>?
}