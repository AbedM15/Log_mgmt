package com.abc.logManagement.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailSenderService {

    @Autowired
    lateinit var mailSender:JavaMailSender

    fun sendMailToSupportEngineer(toEmail:String,body:String,subject:String){
        val message = SimpleMailMessage()
        message.from = "abclogmgmt@gmail.com"
        message.setTo(toEmail)
        message.subject = subject
        message.text = body

        mailSender.send(message)


    }


}