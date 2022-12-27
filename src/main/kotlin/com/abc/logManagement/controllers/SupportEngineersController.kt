package com.abc.logManagement.controllers

import com.abc.logManagement.entities.SupportEngineer
import com.abc.logManagement.responseEntities.SupportEngineerCreated
import com.abc.logManagement.responseEntities.SupportEngineerDeleted
import com.abc.logManagement.services.SupportEngineersServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @DeleteMapping("/delete-by-id/{id}")
    fun deleteSupportEngineerById(@PathVariable id:Long):ResponseEntity<Any>{
        val serviceLayerCall = supportEngineersServiceImpl.deleteSupportEngineerById(id)

        return ResponseEntity.ok().body("Support engineer of id $id has been deleted")
    }


    @DeleteMapping("/delete-by-email")
    fun deleteSupportEngineerByEmail(@RequestParam(name= "email", required = true) email:String):ResponseEntity<Any>{
        val serviceLayerCall = supportEngineersServiceImpl.deleteSupportEngineerByEmail(email)
        return ResponseEntity.ok().body(SupportEngineerDeleted(200,"Support engineer of email $email has been deleted"))


    }



}