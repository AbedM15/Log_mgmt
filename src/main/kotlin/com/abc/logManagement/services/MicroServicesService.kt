package com.abc.logManagement.services

import com.abc.logManagement.entities.Microservice


interface MicroServicesService {

    fun addMicroService(microservice: Microservice): Microservice
    fun deleteMicroServiceById(id: Long)
    fun deleteMicroServiceByName(name: String)
}