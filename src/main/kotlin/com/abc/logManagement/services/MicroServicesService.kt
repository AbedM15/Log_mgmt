package com.abc.logManagement.services

import com.abc.logManagement.entities.Microservice


interface MicroServicesService {

    fun addMicroService(microservice: Microservice): Microservice
    fun deleteMicroServiceById(id: Long)
    fun deleteMicroServiceByName(name: String)
    fun getMicroServiceById(id: Long): Microservice
    fun getAllMicroServices(): List<Microservice>
    fun getMicroServiceByName(name: String): Microservice?
}