package com.abc.logManagement.controllers

import com.abc.logManagement.dto.*
import com.abc.logManagement.services.SupportEngineerServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/s-e")
class SupportEngineerController {

    @Autowired
    lateinit var service:SupportEngineerServiceImpl

    @PostMapping("/create")
    fun createSupportEngineer(@RequestBody supportEngineer:SupportEngineerCreate):ResponseEntity<Any>{
        val engineer = service.createSupportEngineer(supportEngineer)
        return ResponseEntity.status(200).body(SupportEngineerCreatedResponse(200,"Support engineer created",engineer))
    }

    @GetMapping("/get-all")
    fun getAllSupportEngineers():ResponseEntity<Any>{
        val allEngineers:MutableList<AllEngineers> = service.getAllEngineers()
        return ResponseEntity.status(200).body(AllEngineersResponse(200,"Support engineers retrieved successfully",allEngineers))
    }

    @GetMapping("/get-by-id")
    fun getSupportEngineerById(@RequestParam(name = "id") id:Long):ResponseEntity<Any>{
        val supportEng:SupportEngineerRetrieved = service.retrieveEngineerById(id)
        return ResponseEntity.status(200).body(SupportEngineerRetrievedResponse(200,"Support engineer retrieved successfully",supportEng))
    }



}