package com.abc.logManagement.services

import com.abc.logManagement.dto.CreateLogEntry
import com.abc.logManagement.dto.LogCreated

interface LogService {
     fun createLogEntry(log: CreateLogEntry): String
}