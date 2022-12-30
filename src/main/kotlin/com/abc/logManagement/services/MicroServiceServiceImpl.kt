package com.abc.logManagement.services

import com.abc.logManagement.dto.CreateMicroService
import com.abc.logManagement.dto.MicroServiceCreated
import com.abc.logManagement.entities.MicroService
import com.abc.logManagement.exceptions.MicroServiceBadRequest
import com.abc.logManagement.repositories.MicroServicesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MicroServiceServiceImpl:MicroServiceService {

    @Autowired
    lateinit var repo:MicroServicesRepository

    override fun createMicroService(microService: CreateMicroService): MicroServiceCreated {
        when {
            microService.name.isBlank() -> throw MicroServiceBadRequest("Micro service name cannot be empty")

            else-> {

                when{

                    repo.findMicroServiceByName(microService.name)>=1 -> throw MicroServiceBadRequest("Micro service already exists")

                    else -> {

                        val toBeSaved = MicroService(microServiceId = null, microServiceName = microService.name)

                        val saved = repo.save(toBeSaved)

                        return  MicroServiceCreated(microServiceId = saved.microServiceId, microServiceName = saved.microServiceName)

                    }


                }



            }

        }


    }

}