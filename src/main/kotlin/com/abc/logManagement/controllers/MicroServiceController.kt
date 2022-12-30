package com.abc.logManagement.controllers

import com.abc.logManagement.dto.CreateMicroService
import com.abc.logManagement.dto.MicroServiceCreatedResponse
import com.abc.logManagement.services.MicroServiceServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/micro-services")
class MicroServiceController {

    @Autowired
    lateinit var service:MicroServiceServiceImpl

    @PostMapping("/create")
    fun createMicroService(@RequestParam(name = "name") microService: CreateMicroService):ResponseEntity<Any>{

        val microService = service.createMicroService(microService)

        return ResponseEntity.status(200).body(MicroServiceCreatedResponse(200,"Micro service created",microService))
    }


}