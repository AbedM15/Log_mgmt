package com.abc.logManagement.controllers

import com.abc.logManagement.dto.*
import com.abc.logManagement.services.MicroServiceServiceImpl
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
import java.time.LocalDateTime

@WebMvcTest(MicroServiceController::class)
internal class MicroServiceControllerTest {

    @Autowired
    lateinit var mocMvc: MockMvc

    @MockBean
    lateinit var serviceLayer:MicroServiceServiceImpl



    @Test
    @Disabled
    fun callingCreateMicroServicePostFunction(){
        // given
        Mockito.`when`(serviceLayer.createMicroService("Feedback"))
            .thenReturn(MicroServiceCreated(1,"Feedback"))

        // when
        val microService = "Feedback"
        val saved = serviceLayer.createMicroService(microService)
        val response = MicroServiceCreatedResponse(200,"Micro service created",saved)
        val expectedJsonResponse = jacksonObjectMapper().writeValueAsString(response)

        // then
        mocMvc.perform(
            MockMvcRequestBuilders.post("/micro-services/create?name=$microService"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(expectedJsonResponse))
            .andReturn()
    }



    @Test
    @Disabled
    fun callingGetAllMicroServicesGetFunction(){
        //given
        Mockito.`when`(serviceLayer.fetchAllMicroServices()).thenReturn(mutableListOf<AllMicroServices>(
           AllMicroServices(1,"Feedback",null,null),
            AllMicroServices(2,"Shipping",null,null),
            AllMicroServices(3,"Payments",null,null),

            )
        )

        //when
        val microServices = serviceLayer.fetchAllMicroServices()
        val response = AllMicroServicesResponse(200,"Micro services retrieved successfully",microServices)
        val expectedJsonResponse = jacksonObjectMapper().writeValueAsString(response)

        //then
        mocMvc.perform(MockMvcRequestBuilders.get("/micro-services/get-all"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(expectedJsonResponse))
            .andReturn()
    }

    @Test
    @Disabled
    fun callingGetMicroServiceByIdGetFunction(){
        //given
        Mockito.`when`(serviceLayer.fetchMicroServiceById(1L))
            .thenReturn(FetchedMicroService(microServiceId = 1L,
            microServiceName = "Feedback",null,null)
            )

        //when
        val id = 1L
        val microService = serviceLayer.fetchMicroServiceById(id)
        val response = FetchedMicroServiceResponse(200,"Micro service retrieved successfully",microService)
        val expectedJsonResponse = jacksonObjectMapper().writeValueAsString(response)

        //then
        mocMvc.perform(MockMvcRequestBuilders.get("/micro-services/get-by-id?id=$id"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(expectedJsonResponse))
            .andReturn()


    }



}