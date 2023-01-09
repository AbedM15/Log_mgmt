package com.abc.logManagement.services

import com.abc.logManagement.dto.CreateLogEntry
import com.abc.logManagement.entities.Log
import com.abc.logManagement.entities.MicroService
import com.abc.logManagement.exceptions.LogBadRequest
import com.abc.logManagement.repositories.LogsRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDateTime

@SpringBootTest
internal class LogServiceImplTest{

    @MockBean
    lateinit var repository:LogsRepository


    @Autowired
    lateinit var serviceLayer:LogServiceImpl


    @Test
    @Disabled
    fun whenValidNonFatalLogIsEnteredItShouldBeSavedAndLogObjectReturned(){
        val log = CreateLogEntry("info","OK","2023-01-06T15:29:30",1)

        Mockito.`when`(serviceLayer.createLogEntry(log)).thenReturn("Log created")

        val createdLog = serviceLayer.createLogEntry(log)

        assertEquals("Log entry made",createdLog)
    }



    @Test
    @Disabled
    fun whenRequestWithNoLogIsReceivedBadRequestExceptionShouldBeThrown(){
        try {
            serviceLayer.createLogEntry(CreateLogEntry("info","","2023-01-06T15:29:30",9))
        }catch (e:LogBadRequest){
            assertEquals("Log field cannot be empty",e.message)
        }
    }

    @Test
    @Disabled
    fun whenRequestWithInvalidLevelIsReceivedBadRequestExceptionShouldBeThrown(){
        val log = CreateLogEntry("hazard","OK","2023-01-06T15:29:30",9)

        try {
            serviceLayer.createLogEntry(log)
        }catch (e:LogBadRequest){
            assertEquals("Log level ${log.level} is invalid",e.message)
        }
    }


    @Test
    @Disabled
    fun whenRequestWithNoTimeIsReceivedBadRequestExceptionShouldBeThrown(){
        val log = CreateLogEntry("info","OK","",9)

        try {
            serviceLayer.createLogEntry(log)
        }catch (e:LogBadRequest){
            assertEquals("Log time entered is invalid",e.message)
        }
    }

    @Test
    @Disabled
    fun whenRequestWithInvalidTimeIsReceivedBadRequestExceptionShouldBeThrown(){
        val log = CreateLogEntry("info","OK","567",9)

        try {
            serviceLayer.createLogEntry(log)
        }catch (e:LogBadRequest){
            assertEquals("Date time value provided is invalid",e.message)
        }
    }





}