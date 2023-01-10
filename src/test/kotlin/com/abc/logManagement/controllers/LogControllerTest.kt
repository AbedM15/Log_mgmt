package com.abc.logManagement.controllers

import com.abc.logManagement.dto.CreateLogEntry
import com.abc.logManagement.dto.LogCreated
import com.abc.logManagement.dto.LogsByValueResponse
import com.abc.logManagement.dto.SearchByLogsResponse
import com.abc.logManagement.services.LogServiceImpl
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(LogController::class)
internal class LogControllerTest {

    @Autowired
    lateinit var mocMvc:MockMvc

    @MockBean
    lateinit var serviceLayer:LogServiceImpl


    val objectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())

    @Test
    @Disabled
    fun callingCreateLogPostFunction(){
        // given
        Mockito.`when`(serviceLayer.createLogEntry(CreateLogEntry(level = "info", log = "ok", time = "2023-01-06T15:29:30", microServiceId = 1))).thenReturn("Log entry made")

        // when
        val log = CreateLogEntry(level = "info", log = "ok", time = "2023-01-06T15:29:30", microServiceId = 1)
        val jsonString = jacksonObjectMapper().writeValueAsString(log)
        val saved = serviceLayer.createLogEntry(log)
        val response = LogCreated(200,saved)
        val expectedJsonResponse = jacksonObjectMapper().writeValueAsString(response)

        // then
        mocMvc.perform(MockMvcRequestBuilders.post("/logs/create-log")
            .content(jsonString)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().json(expectedJsonResponse))
            .andReturn()
    }

    @Test
    @Disabled
    fun callingSearchByValueEndPoint(){
        //given
        Mockito.`when`(serviceLayer.getLogsByValue("info")).thenReturn(mutableListOf<SearchByLogsResponse>(
        SearchByLogsResponse(1,"info",null,"ok", LocalDateTime.parse("2023-01-20T15:29:30"),1,"Feedback"),
            SearchByLogsResponse(2,"info",null,"ok", LocalDateTime.parse("2023-01-16T15:29:30"),1,"Feedback"),
            SearchByLogsResponse(3,"info",null,"ok", LocalDateTime.parse("2023-01-15T15:29:30"),1,"Feedback"),
            SearchByLogsResponse(4,"info",null,"ok", LocalDateTime.parse("2023-01-10T15:29:30"),1,"Feedback")
        ))

        //when
        val value = "info"
        val logs = serviceLayer.getLogsByValue(value)
        val response = LogsByValueResponse(200,"Logs Retrieved Successfully",logs)
        val expectedJsonResponse = objectMapper.writeValueAsString(response)

        //then
        mocMvc.perform(MockMvcRequestBuilders.get("/logs/search-by-value?value=$value"))
            .andExpect(status().isOk)
            .andExpect(content().json(expectedJsonResponse))
            .andReturn()

    }







}