package com.abc.logManagement.services

import com.abc.logManagement.entities.Microservice
import com.abc.logManagement.exceptions.MicroServiceNameIsBlankOrNull
import com.abc.logManagement.repositories.MicroServicesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MicroServicesServiceImpl: MicroServicesService {

    @Autowired
    lateinit var microServicesRepository: MicroServicesRepository


    override fun addMicroService(microservice: Microservice):Microservice{

    if(microservice.microServiceName.isNullOrBlank() ) {
        throw MicroServiceNameIsBlankOrNull("Micro service name field cannot be empty or equal to null")
    }else{
        return microServicesRepository.save(microservice)
    }



    }



}