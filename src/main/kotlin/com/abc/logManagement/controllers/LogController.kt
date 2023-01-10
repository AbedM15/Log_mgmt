package com.abc.logManagement.controllers

import com.abc.logManagement.dto.CreateLogEntry
import com.abc.logManagement.dto.LogCreated
import com.abc.logManagement.dto.LogsByValueResponse
import com.abc.logManagement.services.LogServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/logs")
class LogController {

    @Autowired
    lateinit var service:LogServiceImpl

    @PostMapping("/create-log")
    fun createLogEntry(@RequestBody log:CreateLogEntry):ResponseEntity<Any>{
        val logEntered = service.createLogEntry(log)
        return ResponseEntity.status(200).body(LogCreated(200,logEntered))
    }

    @GetMapping("/search-by-value")
    fun searchByValue(@RequestParam(name = "value") value:String):ResponseEntity<Any>{
        val logList = service.getLogsByValue(value)

        return ResponseEntity.status(200).body(LogsByValueResponse(200,"Logs Retrieved Successfully",logList))

    }








}