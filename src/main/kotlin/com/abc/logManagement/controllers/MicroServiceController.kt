package com.abc.logManagement.controllers

import com.abc.logManagement.dto.*
import com.abc.logManagement.services.MicroServiceServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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


    @GetMapping("/get-all")
    fun getAllMicroServices():ResponseEntity<Any>{
        val allMicroServices:MutableList<AllMicroServices>? = service.fetchAllMicroServices()
        return ResponseEntity.status(200).body(AllMicroServicesResponse(200,"Micro services retrieved successfully",allMicroServices))
    }

    @GetMapping("/get-by-id")
    fun getMicroServiceById(@RequestParam(name = "id") id:Long):ResponseEntity<Any>{
        val microService:FetchedMicroService = service.fetchMicroServiceById(id)
        return ResponseEntity.ok().body(FetchedMicroServiceResponse(200,"Micro service retrieved successfully",microService))
    }


}