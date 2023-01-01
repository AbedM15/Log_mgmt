package com.abc.logManagement.services

import com.abc.logManagement.dto.CreateLogEntry
import com.abc.logManagement.dto.LogCreated
import com.abc.logManagement.entities.Log
import com.abc.logManagement.entities.MicroService
import com.abc.logManagement.entities.SupportEngineer
import com.abc.logManagement.exceptions.LogBadRequest
import com.abc.logManagement.repositories.LogsRepository
import com.abc.logManagement.repositories.MicroServicesRepository
import com.abc.logManagement.repositories.SupportEngineersRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Service
class LogServiceImpl:LogService {

    @Autowired
    lateinit var emailSender:EmailSenderService

    @Autowired
    lateinit var supportEngineerRepo:SupportEngineersRepository


    @Autowired
    lateinit var repo:LogsRepository

    @Autowired
    lateinit var microServiceRepo:MicroServicesRepository

    var logger:Logger = LoggerFactory.getLogger(LogServiceImpl::class.java)

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
                    return if(log.level.lowercase() != "fatal"){
                        "Log entry made"
                    }else{
                        val body:String = "A fatal log has been received. Micro Service name: ${microService.microServiceName} time: ${LocalDateTime.parse(log.time,formatter)}"
                        val subject:String = "Fatal Log Received"
                        val mails = retrieveEngineersEmails(microService)
                        mailSupportEngineersIfFatal(body = body, subject = subject, mailList = mails)
                        "Fatal log entry made and support engineers alerted"
                    }
                }
            }
        }else{
            throw LogBadRequest("Log level ${log.level} is invalid")
        }
    }


    fun mailSupportEngineersIfFatal(body:String,subject:String, mailList:List<String>){
        logger.info("INSIDE SENDER")

        for(m in mailList) {
            emailSender.sendMailToSupportEngineer(toEmail = m, body = body,subject = subject )
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


    fun retrieveEngineersEmails(microService: MicroService):List<String>{
        val ids = microServiceRepo.findSupportEngineerIdsByMicroServiceId(microService.microServiceId!!)
        val idsCounter = ids!!.count().toLong()
        val mails = mutableListOf<String>()

        for (id in ids){
            mails.add(supportEngineerRepo.findById(id).get().emailAddress)
        }
        return mails
    }


}