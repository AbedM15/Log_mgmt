package com.abc.logManagement.controllers

import com.abc.logManagement.dto.SupportEngineerCreate
import com.abc.logManagement.dto.SupportEngineerCreatedResponse
import com.abc.logManagement.services.SupportEngineerServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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



}