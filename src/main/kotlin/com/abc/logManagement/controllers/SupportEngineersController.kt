package com.abc.logManagement.controllers

import com.abc.logManagement.entities.SupportEngineer
import com.abc.logManagement.responseEntities.SupportEngineerCreated
import com.abc.logManagement.services.SupportEngineersServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/s-e")
class SupportEngineersController {

    @Autowired
    lateinit var supportEngineersServiceImpl: SupportEngineersServiceImpl

    @PostMapping("/add")
    fun addSupportEngineer(@RequestBody supportEngineer: SupportEngineer):ResponseEntity<Any>{
        val serviceLayerCall = supportEngineersServiceImpl.addSupportEngineer(supportEngineer)

        return ResponseEntity.ok().body(SupportEngineerCreated(200,"Support Engineer Created",serviceLayerCall))


    }


}