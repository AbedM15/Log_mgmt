package com.abc.logManagement.services

import com.abc.logManagement.dto.CreateLogEntry
import com.abc.logManagement.dto.LogCreated
import com.abc.logManagement.entities.Log
import com.abc.logManagement.exceptions.LogBadRequest
import com.abc.logManagement.repositories.LogsRepository
import com.abc.logManagement.repositories.MicroServicesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Service
class LogServiceImpl:LogService {

    @Autowired
    lateinit var repo:LogsRepository

    @Autowired
    lateinit var microServiceRepo:MicroServicesRepository

    override fun createLogEntry(log: CreateLogEntry): String {
        val validLevels = listOf("info","debug","error","fatal")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        if(log.level.lowercase() in (validLevels)){
            when{
                log.log.isBlank() -> throw LogBadRequest("Log field cannot be empty")
                log.time.isBlank() -> throw LogBadRequest("Log time entered is invalid")
                !isValidLocalDateTime(log.time) -> throw LogBadRequest("Date time value provided is invalid")
                log.microService.microServiceId.toString().isBlank()  -> throw LogBadRequest("Micro service id is a required field")
                !microServiceRepo.findById(log.microService.microServiceId!!).isPresent -> throw LogBadRequest("Micro service with id ${log.microService.microServiceId} does not exist")
                else ->  {
                    val microService = microServiceRepo.findById(log.microService.microServiceId!!).get()
                    val toBeSaved = Log(logId = null, level = log.level.uppercase(),
                    resolution = if (log.level.lowercase() == "fatal") "unresolved" else null,
                    log = log.log, time = LocalDateTime.parse(log.time,formatter), microService = microService)
                    repo.save(toBeSaved)
                    return "Log entry made"
                }
            }
        }else{
            throw LogBadRequest("Log level ${log.level} is invalid")
        }
    }


    fun isValidLocalDateTime(dateTimeString: String): Boolean {
        return try {
            LocalDateTime.parse(dateTimeString)
            true
        } catch (e: DateTimeParseException) {
            false
        }
    }


}