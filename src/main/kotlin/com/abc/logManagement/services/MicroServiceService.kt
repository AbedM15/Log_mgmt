package com.abc.logManagement.services

import com.abc.logManagement.dto.CreateMicroService
import com.abc.logManagement.dto.MicroServiceCreated

interface MicroServiceService {
     fun createMicroService(microService: CreateMicroService): MicroServiceCreated
}