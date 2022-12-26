package com.abc.logManagement.controllers

import com.abc.logManagement.entities.Microservice
import com.abc.logManagement.responseEntities.AllMicroServices
import com.abc.logManagement.responseEntities.MicroServiceCreated
import com.abc.logManagement.responseEntities.MicroServiceDeleted
import com.abc.logManagement.responseEntities.MicroServiceRetrieved
import com.abc.logManagement.services.MicroServicesServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/micro-services")
class MicroServicesController {

    @Autowired
    lateinit var microServicesServiceImpl: MicroServicesServiceImpl

    var logger: Logger = LoggerFactory.getLogger(MicroServicesController::class.java)


    @PostMapping("/add")
    fun addMicroService(@RequestBody microService: Microservice): ResponseEntity<Any> {
        val serviceLayerCall = microServicesServiceImpl.addMicroService(microService)

        val microServiceCreated = MicroServiceCreated(200,
            "Microservice created successfully",serviceLayerCall)

        return ResponseEntity.ok().body(microServiceCreated)


    }

    @DeleteMapping("/delete-by-id/{id}")
    fun deleteById(@PathVariable id: Long):ResponseEntity<Any>{
        val serviceLayerCall = microServicesServiceImpl.deleteMicroServiceById(id)

        return ResponseEntity.ok().body(MicroServiceDeleted(200,"Micro service $id has been deleted"))

    }

    @DeleteMapping("/delete-by-name/{name}")
        fun deleteByName(@PathVariable name : String):ResponseEntity<Any>{
        val serviceLayerCall = microServicesServiceImpl.deleteMicroServiceByName(name)
        return ResponseEntity.ok().body(MicroServiceDeleted(200,"Micro service $name has been deleted"))
    }

    @GetMapping("/get-by-id/{id}")
    fun getById(@PathVariable id: Long):ResponseEntity<Any>{
        val serviceLayerCall = microServicesServiceImpl.getMicroServiceById(id)

        return ResponseEntity.ok().body(MicroServiceRetrieved(200,serviceLayerCall))

    }


    @GetMapping("/get-all")
    fun getAll():ResponseEntity<Any>{
        val serviceLayerCall = microServicesServiceImpl.getAllMicroServices()

        return ResponseEntity.ok().body(AllMicroServices(200,serviceLayerCall))
    }

    @GetMapping("/get-by-name/{name}")
    fun getMicroServiceByName(@PathVariable name: String):ResponseEntity<Any>{
        val serviceLayerCall = microServicesServiceImpl.getMicroServiceByName(name)
        return ResponseEntity.ok().body(MicroServiceRetrieved(200,serviceLayerCall))
    }




}