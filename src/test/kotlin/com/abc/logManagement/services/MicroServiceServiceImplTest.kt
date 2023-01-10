package com.abc.logManagement.services

import com.abc.logManagement.dto.AllMicroServices
import com.abc.logManagement.dto.CreateMicroService
import com.abc.logManagement.dto.MicroServiceByIdSupportEngineer
import com.abc.logManagement.entities.MicroService
import com.abc.logManagement.entities.SupportEngineer
import com.abc.logManagement.exceptions.MicroServiceBadRequest
import com.abc.logManagement.exceptions.MicroServiceNotFound
import com.abc.logManagement.repositories.MicroServicesRepository
import com.abc.logManagement.repositories.SupportEngineersRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
internal class MicroServiceServiceImplTest {

    @MockBean
    lateinit var repositoryLayer:MicroServicesRepository

    @MockBean
    lateinit var supportEngineerRepo:SupportEngineersRepository

    @Autowired
    lateinit var serviceLayer:MicroServiceServiceImpl

    @BeforeEach
    fun setUp() {
        val microService = MicroService(1L,"Customer Feedback",
        null,null)

        Mockito.`when`(repositoryLayer.save(MicroService(null,"Customer Feedback",
        null,null))).thenReturn(microService)


    }

    @Test
    @Disabled
    fun whenValidMicroServiceSavedMicroServiceShouldBeReturned(){
        val createMicroService = "Customer Feedback"
        val savedMicroService = repositoryLayer.save(MicroService(null,createMicroService,
            null,null))
        assertEquals(1L,savedMicroService.microServiceId)
    }

    @Test
    @Disabled
    fun testCreateMicroService_throwsBadRequestIfNameIsBlank() {
        val microService = ""

        try {
            serviceLayer.createMicroService(microService)
            fail("Expected MicroServiceBadRequest exception")
        } catch (e: MicroServiceBadRequest) {
            assertEquals("Micro service name cannot be empty", e.message)
        }
    }


    @Test
    @Disabled
    fun testCreateMicroService_throwBadRequestIfMicroServiceWithSameNameExists(){
        val createMicroService = "Test MicroService"
        Mockito.`when`(repositoryLayer.findMicroServiceByName("Test MicroService")).thenReturn(1)

        try {
            serviceLayer.createMicroService(createMicroService)
            fail("Expected MicroServiceBadRequest exception")

        } catch (e:MicroServiceBadRequest){
            assertEquals("Micro service already exists",e.message)
        }
    }

    @Test
    @Disabled
    fun whenFetchAllMicroServicesIsCalledAMutableListOfAllMicroServicesShouldBeReturned(){
        val microServices = mutableListOf(
            MicroService(1,"test1",null,null),
        MicroService(2,"test2",null,null),
        MicroService(3,"test3",null,null)
        )

        Mockito.`when`(repositoryLayer.findAll()).thenReturn(microServices)

        val fetchedMicroServices = repositoryLayer.findAll()

        assertEquals(3,fetchedMicroServices.size)
        assertEquals(microServices[0].microServiceId,fetchedMicroServices[0].microServiceId)
        assertEquals(microServices[1].microServiceId,fetchedMicroServices[1].microServiceId)
        assertEquals(microServices[2].microServiceId,fetchedMicroServices[2].microServiceId)
    }

    @Test
    @Disabled
    fun whenTryingToFetchMicroServiceWithIdThatDoesNotExistAnExceptionShouldBeThrown(){
        val id = 6L
        Mockito.`when`(repositoryLayer.findById(6)).thenReturn(Optional.empty())
        try{
          serviceLayer.fetchMicroServiceById(id)
          fail("Expected MicroServiceNotFound exception")
        }catch (e:MicroServiceNotFound){
            assertEquals("Micro service not found", e.message)
        }
    }

    @Test
    @Disabled
    fun whenTryingToFetchMicroServiceWithIdThatExistsTheMicroServiceShouldBeReturned(){
        val id = 1L

        Mockito.`when`(repositoryLayer.findById(1L)).thenReturn(Optional.of(
            MicroService(1L,"Test Micro Service",null,null)))

        assertNotNull(repositoryLayer.findById(id))
        assertEquals("Test Micro Service",repositoryLayer.findById(1L).get().microServiceName)

    }


    @Test
    @Disabled
    fun whenTryingToFetchSupportEngineersByMicroServiceIdListOfSupportEngineerIdsShouldBeReturned(){
        val microServiceId = 1L

        Mockito.`when`(repositoryLayer.findSupportEngineerIdsByMicroServiceId(1L)).thenReturn(
            listOf(1,2,3,4)
        )

        val supportEngineersIds = repositoryLayer.findSupportEngineerIdsByMicroServiceId(1L)

        assertEquals(4,supportEngineersIds!!.size)
    }





}