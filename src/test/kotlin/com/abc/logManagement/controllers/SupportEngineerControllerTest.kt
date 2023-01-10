package com.abc.logManagement.controllers

import com.abc.logManagement.dto.*
import com.abc.logManagement.entities.SupportEngineer
import com.abc.logManagement.repositories.SupportEngineersRepository
import com.abc.logManagement.services.SupportEngineerServiceImpl
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

@WebMvcTest(SupportEngineerController::class)
internal class SupportEngineerControllerTest{

    @Autowired
    lateinit var mocMvc: MockMvc

    @MockBean
    lateinit var serviceLayer:SupportEngineerServiceImpl

    @Test
    @Disabled
    fun callingCreateSupportEngineerPostFunction(){
        // given
        Mockito.`when`(serviceLayer.createSupportEngineer(
            SupportEngineerCreate("John","Doe","jd@ggmail.com",1)))
            .thenReturn(
                SupportEngineerCreated(1,"John","Doe","jd@gmail.com",
                CreatedSupportEngineerMicroService(1,"Feedback")
                ))

        // when
        val supportEngineer = SupportEngineerCreate("John","Doe","jd@ggmail.com",1)
        val jsonString = jacksonObjectMapper().writeValueAsString(supportEngineer)
        val saved:SupportEngineerCreated = serviceLayer.createSupportEngineer(supportEngineer)
        val response = SupportEngineerCreatedResponse(200,"Support engineer created",saved)
        val expectedJsonResponse = jacksonObjectMapper().writeValueAsString(response)

        // then
        mocMvc.perform(
            MockMvcRequestBuilders.post("/s-e/create")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(expectedJsonResponse))
            .andReturn()
    }


    @Test
    @Disabled
    fun callingGetAllGetAllSupportEngineersGetEndPoint(){
        //given
        Mockito.`when`(serviceLayer.getAllEngineers()).thenReturn(mutableListOf(
            AllEngineers(1,"John","Doe","doe@gmail.com"),
            AllEngineers(2,"Oliver","Twist","otwist@gmail.com"),
            AllEngineers(3,"harry","harry","harry@gmail.com"),
            AllEngineers(4,"sally","season","season430@gmail.com"),
            AllEngineers(5,"son","Junior","sj@gmail.com"),



            )
        )

        //when
        val supportEngineers = serviceLayer.getAllEngineers()
        val response = AllEngineersResponse(200,"Support engineers retrieved successfully",supportEngineers)
        val expectedJsonResponse = jacksonObjectMapper().writeValueAsString(response)

        //then
        mocMvc.perform(MockMvcRequestBuilders.get("/s-e/get-all"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(expectedJsonResponse))
            .andReturn()
    }


    @Test
    @Disabled
    fun callingGetSupportEngineerByIdGetEndpoint(){
        //given
        Mockito.`when`(serviceLayer.retrieveEngineerById(1L))
            .thenReturn(
                SupportEngineerRetrieved(3,"harry","harry","harry@gmail.com")
            )

        //when
        val id = 1L
        val supportEngineer = serviceLayer.retrieveEngineerById(id)
        val response = SupportEngineerRetrievedResponse(200,"Support engineer retrieved successfully",supportEngineer)
        val expectedJsonResponse = jacksonObjectMapper().writeValueAsString(response)

        //then
        mocMvc.perform(MockMvcRequestBuilders.get("/s-e/get-by-id?id=$id"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(expectedJsonResponse))
            .andReturn()


    }






}