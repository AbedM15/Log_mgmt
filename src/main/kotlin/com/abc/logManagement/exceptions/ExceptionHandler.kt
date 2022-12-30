package com.abc.logManagement.exceptions

import com.abc.logManagement.dto.MicroServiceBadRequestResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun microServiceBadRequest(exception:MicroServiceBadRequest):ResponseEntity<Any>{
        return ResponseEntity.status(400).body(MicroServiceBadRequestResponse(400,exception.message.toString()))
    }


}