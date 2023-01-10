package com.abc.logManagement.services

import com.abc.logManagement.dto.AllMicroServices
import com.abc.logManagement.dto.CreateMicroService
import com.abc.logManagement.dto.FetchedMicroService
import com.abc.logManagement.dto.MicroServiceCreated

interface MicroServiceService {
     fun createMicroService(microService: String): MicroServiceCreated
     fun fetchAllMicroServices(): MutableList<AllMicroServices>?
     fun fetchMicroServiceById(id: Long): FetchedMicroService
}