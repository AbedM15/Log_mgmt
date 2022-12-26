package com.abc.logManagement.exceptions

import com.abc.logManagement.responseEntities.MicroServiceBadRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
@ResponseStatus
class ExceptionHandler {

    @ExceptionHandler(MicroServiceNameIsBlankOrNull::class)
    fun microServiceNameIsBlankOrNull(exception:MicroServiceNameIsBlankOrNull):ResponseEntity<Any>{
        val message = MicroServiceBadRequest(400,exception.message.toString())
        return ResponseEntity.status(400).body(message)
    }

    @ExceptionHandler(MicroServiceAlreadyExists::class)
    fun microServiceAlreadyExists(exception:MicroServiceAlreadyExists):ResponseEntity<Any>{
        val message = MicroServiceBadRequest(400,exception.message.toString())
        return ResponseEntity.status(400).body(message)
    }
    @ExceptionHandler(MicroServiceDoesNotExist::class)
    fun microServiceDoesNotExist(exception: MicroServiceDoesNotExist):ResponseEntity<Any>{
        val message = MicroServiceBadRequest(404,exception.message.toString())
        return ResponseEntity.status(404).body(message)
    }



}