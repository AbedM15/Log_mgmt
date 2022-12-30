package com.abc.logManagement.exceptions

import com.abc.logManagement.dto.MicroServiceBadRequestResponse
import com.abc.logManagement.dto.MicroServiceNotFoundResponse
import com.abc.logManagement.dto.SupportEngineerBadRequestResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun microServiceBadRequest(exception:MicroServiceBadRequest):ResponseEntity<Any>{
        return ResponseEntity.status(400).body(MicroServiceBadRequestResponse(400,exception.message.toString()))
    }

    @ExceptionHandler
    fun microServiceNotFound(exception:MicroServiceNotFound):ResponseEntity<Any>{
        return ResponseEntity.status(404).body(MicroServiceNotFoundResponse(404,exception.message.toString()))
    }

    @ExceptionHandler
    fun supportEngineerBadRequest(exception:SupportEngineerBadRequest):ResponseEntity<Any>{
        return ResponseEntity.status(400).body(SupportEngineerBadRequestResponse(400,exception.message.toString()))
    }



}