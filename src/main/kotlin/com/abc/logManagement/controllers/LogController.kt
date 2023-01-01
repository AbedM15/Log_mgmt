package com.abc.logManagement.controllers

import com.abc.logManagement.dto.CreateLogEntry
import com.abc.logManagement.dto.LogCreated
import com.abc.logManagement.services.LogServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/logs")
class LogController {

    @Autowired
    lateinit var service:LogServiceImpl

    @PostMapping("/create-log")
    fun createLogEntry(@RequestBody log:CreateLogEntry):ResponseEntity<Any>{
        val log = service.createLogEntry(log)
        return ResponseEntity.status(200).body(LogCreated(200,log))
    }






}