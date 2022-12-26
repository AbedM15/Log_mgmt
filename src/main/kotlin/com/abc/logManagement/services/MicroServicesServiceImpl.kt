package com.abc.logManagement.services

import com.abc.logManagement.entities.Microservice
import com.abc.logManagement.exceptions.MicroServiceAlreadyExists
import com.abc.logManagement.exceptions.MicroServiceDoesNotExist
import com.abc.logManagement.exceptions.MicroServiceNameIsBlankOrNull
import com.abc.logManagement.repositories.MicroServicesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MicroServicesServiceImpl: MicroServicesService {

    @Autowired
    lateinit var microServicesRepository: MicroServicesRepository


    override fun addMicroService(microservice: Microservice):Microservice{

        val presentMicro:Microservice? = microServicesRepository.findByMicroServiceNameIgnoreCase(microservice.microServiceName.toString())


    if(microservice.microServiceName.isNullOrBlank() ) {
        throw MicroServiceNameIsBlankOrNull("Micro service name field cannot be empty or equal to null")
    }else if (presentMicro!=null){
        throw MicroServiceAlreadyExists("Micro service: ${microservice.microServiceName} already exists")
    }else {
        return microServicesRepository.save(microservice)
    }

    }

    override fun deleteMicroServiceById(id: Long) {

            val repositoryLayerCall = microServicesRepository.findById(id)

            if (!repositoryLayerCall.isPresent) {
                throw MicroServiceDoesNotExist("Micro service of id: $id does not exist ")

            } else {
                return microServicesRepository.deleteById(id)
            }


    }

    override fun deleteMicroServiceByName(name: String) {

        if(name.isBlank()){
            throw MicroServiceNameIsBlankOrNull("Micro service name cannot be empty")
        }

        if(microServicesRepository.findByMicroServiceNameIgnoreCase(name) == null){
            throw MicroServiceDoesNotExist("Micro service does not exist")
        }else{
            return microServicesRepository.deleteByMicroServiceName(name)
        }


    }

    override fun getMicroServiceById(id: Long): Microservice {

        if (!microServicesRepository.findById(id).isPresent){
            throw MicroServiceDoesNotExist("Micro service does not exist")
        }else{
            return microServicesRepository.findById(id).get()
        }


    }
    override fun getAllMicroServices(): List<Microservice> {
        return microServicesRepository.findAll()
    }

    override fun getMicroServiceByName(name: String): Microservice? {
        if (name.isBlank()){
            throw MicroServiceNameIsBlankOrNull("Micro service name cannot be empty")
        }else {
            val repositoryLayerCall = microServicesRepository.findByMicroServiceNameIgnoreCase(name)
            if (repositoryLayerCall!!.microServiceName == null) {
                throw MicroServiceDoesNotExist("Micro service does not exist")
            } else {
                return repositoryLayerCall
            }
        }
    }



}