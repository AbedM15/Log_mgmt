package com.abc.logManagement.controllers

import com.abc.logManagement.entities.Microservice
import com.abc.logManagement.responseEntities.MicroServiceCreated
import com.abc.logManagement.services.MicroServicesServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/micro-services")
class MicroServicesController {

    @Autowired
    lateinit var microServicesServiceImpl: MicroServicesServiceImpl

    @PostMapping("/add")
    fun addMicroService(@RequestBody microService: Microservice): ResponseEntity<Any> {
        val serviceLayerCall = microServicesServiceImpl.addMicroService(microService)

        val microServiceCreated = MicroServiceCreated(201,
            "Microservice created successfully",serviceLayerCall)

        return ResponseEntity.ok().body(microServiceCreated)

    }



}