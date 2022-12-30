package com.abc.logManagement.controllers

import com.abc.logManagement.services.LogServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/logs")
class LogController {

    @Autowired
    lateinit var service:LogServiceImpl




}