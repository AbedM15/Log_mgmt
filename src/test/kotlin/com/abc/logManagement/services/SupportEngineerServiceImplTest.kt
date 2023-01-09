package com.abc.logManagement.services

import com.abc.logManagement.dto.CreatedSupportEngineerMicroService
import com.abc.logManagement.dto.SupportEngineerCreate
import com.abc.logManagement.dto.SupportEngineerCreated
import com.abc.logManagement.entities.MicroService
import com.abc.logManagement.entities.SupportEngineer
import com.abc.logManagement.exceptions.SupportEngineerBadRequest
import com.abc.logManagement.repositories.MicroServicesRepository
import com.abc.logManagement.repositories.SupportEngineersRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
internal class SupportEngineerServiceImplTest{

    @Autowired
    lateinit var serviceLayer: SupportEngineerServiceImpl

    @MockBean
    lateinit var repo:SupportEngineersRepository

    @MockBean
    lateinit var microServiceRepo:MicroServicesRepository



    @BeforeEach
    fun setUp(){
        Mockito.`when`(microServiceRepo.findById(5)).
        thenReturn(Optional.of(MicroService(5,"Feedback",null,null)))


    }


    @Test
    @Disabled
    fun whenValidSupportEngineerSaveAndSavedObjectShouldBeReturned(){

        val supportEngineer: SupportEngineerCreate = SupportEngineerCreate("John","Doe",
        "jd@gmail.com",5)

        Mockito.`when`(repo.save(SupportEngineer(null,supportEngineer.firstName,supportEngineer.lastName,
        supportEngineer.emailAddress, mutableSetOf(microServiceRepo.findById(supportEngineer.microServiceId).get()))))
            .thenReturn(SupportEngineer(1L,"John","Doe","jd@gmail.com",null))

        val microService = microServiceRepo.findById(5).get()

        val savedEngineer = repo.save(SupportEngineer(
            id = null, firstName = supportEngineer.firstName, lastName = supportEngineer.lastName,
            emailAddress = supportEngineer.emailAddress, mutableSetOf(microService)
        ))

        assertEquals(1L,savedEngineer.id)

    }

    @Test
    @Disabled
    fun whenFirstNameNotPresentServiceLayerShouldThrowBadRequest(){
        val  supportEngineer: SupportEngineerCreate = SupportEngineerCreate(lastName = "Doe",
            emailAddress = "jd@gmail.com", microServiceId = 5, firstName = "")
        try {
            serviceLayer.createSupportEngineer(supportEngineer)
        }catch (e:SupportEngineerBadRequest){
            assertEquals("First Name field cannot be blank",e.message)
        }

    }

    @Test
    @Disabled
    fun whenLastNameNotPresentServiceLayerShouldThrowBadRequest(){
        val  supportEngineer: SupportEngineerCreate = SupportEngineerCreate(lastName = "",
            emailAddress = "jd@gmail.com", microServiceId = 5, firstName = "John")
        try {
            serviceLayer.createSupportEngineer(supportEngineer)
        }catch (e:SupportEngineerBadRequest){
            assertEquals("Last Name field cannot be blank",e.message)
        }

    }

    @Test
    @Disabled
    fun whenEmailNotPresentServiceLayerShouldThrowBadRequest(){
        val  supportEngineer: SupportEngineerCreate = SupportEngineerCreate(lastName = "duty",
            emailAddress = "", microServiceId = 5, firstName = "John")
        try {
            serviceLayer.createSupportEngineer(supportEngineer)
        }catch (e:SupportEngineerBadRequest){
            assertEquals("Email Address field cannot be blank",e.message)
        }

    }

    @Test
    @Disabled
    fun whenInvalidEmailServiceLayerShouldThrowBadRequest(){
        val  supportEngineer: SupportEngineerCreate = SupportEngineerCreate(lastName = "duty",
            emailAddress = "k", microServiceId = 5, firstName = "John")
        try {
            serviceLayer.createSupportEngineer(supportEngineer)
        }catch (e:SupportEngineerBadRequest){
            assertEquals("Email entered is not a valid address",e.message)
        }

    }



    @Test
    @Disabled
    fun supportEngineerAlreadyExistsServiceLayerShouldThrowBadRequest(){
        val  supportEngineer: SupportEngineerCreate = SupportEngineerCreate(lastName = "duty",
            emailAddress = "k@gmail.com", microServiceId = 5, firstName = "John")

        Mockito.`when`(repo.findEngineerByEmail("k@gmail.com")).thenReturn(1)

        try {
            serviceLayer.createSupportEngineer(supportEngineer)
        }catch (e:SupportEngineerBadRequest){
            assertEquals("Support engineer with email ${supportEngineer.emailAddress} already exists",e.message)
        }

    }


    @Test
    @Disabled
    fun whenGetAllSupportEngineersIsCalledAMutableListOfSupportEngineersShouldBeReturned(){
        Mockito.`when`(repo.findAll()).thenReturn(
            mutableListOf(
            SupportEngineer(1,"John","Doe","j@gmail.com",null),
                SupportEngineer(2,"Sally","Sang","ss@gmail.com",null),
                ))

        assertEquals(2,repo.findAll().size)

    }


    @Test
    @Disabled
    fun whenGetSupportEngineerByIdIsCalledWithAValidIdASupportEngineerObjectShouldBeReturned(){
        Mockito.`when`(repo.findById(60L)).thenReturn(Optional.of(
            SupportEngineer(60L,"Timothy","Omondi","timondi@gmail.com",null)))

        val supportEngineerId = 60L

        assertEquals("Omondi",repo.findById(supportEngineerId).get().lastName)
    }





}