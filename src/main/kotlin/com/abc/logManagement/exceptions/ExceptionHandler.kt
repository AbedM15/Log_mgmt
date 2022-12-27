package com.abc.logManagement.exceptions

import com.abc.logManagement.responseEntities.MicroServiceBadRequest
import com.abc.logManagement.responseEntities.SupportEngineerBadRequest
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

    @ExceptionHandler(SupportEngineerRequiredField::class)
    fun emailAddressIsARequiredField(exception:SupportEngineerRequiredField):ResponseEntity<Any>{
        return ResponseEntity.status(400).body(SupportEngineerBadRequest(400,exception.message.toString()))
    }

    @ExceptionHandler(InvalidEmailAddress::class)
    fun invalidEmailAddress(exception:InvalidEmailAddress):ResponseEntity<Any>{
        return ResponseEntity.status(400).body(SupportEngineerBadRequest(400,exception.message.toString()))
    }

    @ExceptionHandler(SupportEngineerAlreadyExists::class)
    fun supportEngineerAlreadyExists(exception:SupportEngineerAlreadyExists):ResponseEntity<Any>{
        return ResponseEntity.status(400).body(SupportEngineerBadRequest(400,exception.message.toString()))
    }

    @ExceptionHandler(SupportEngineerDoesNotExist::class)
    fun supportEngineerDoesNotExist(exception:SupportEngineerDoesNotExist):ResponseEntity<Any>{
        return ResponseEntity.status(404).body(SupportEngineerBadRequest(404,exception.message.toString()))
    }




}