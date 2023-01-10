package com.abc.logManagement.repositories

import com.abc.logManagement.dto.SupportEngineerCreate
import com.abc.logManagement.entities.MicroService
import com.abc.logManagement.entities.SupportEngineer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@DataJpaTest
class SupportEngineersRepositoryTests {

    @Autowired
    lateinit var repo:SupportEngineersRepository



    @BeforeEach
    fun setUp(){
        repo.saveAllAndFlush(listOf(
        SupportEngineer(1,"John","Doe","jd@mail.com", mutableSetOf(MicroService(1,"Feedback",null,null))),
            SupportEngineer(2,"Adam","Taylor","at@mail.com", mutableSetOf(MicroService(1,"Feedback",null,null)))

        )

        )
    }


    @Test
    @Disabled
    fun getAllEngineersTest() {
        assertTrue(repo.findAll().size  == 2)
    }


    @Test
    @Disabled
    fun getSupportEngineerById(){
        assertTrue(repo.findById(2L).isPresent)
    }







}