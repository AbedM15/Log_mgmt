package com.abc.logManagement.repositories

import com.abc.logManagement.entities.MicroService
import com.abc.logManagement.entities.SupportEngineer
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class MicroServicesRepositoryTests {

    @Autowired
    lateinit var repo:MicroServicesRepository


    @BeforeEach
    fun setUp(){
        repo.saveAllAndFlush(listOf(
            MicroService(1L,"Feedback",null,null),
            MicroService(2L,"Shipping",null,null)

        )

        )
    }


    @Test
    @Disabled
    fun fetchMicroServiceById(){
        assertTrue(repo.findById(2L).isPresent)
    }

    @Test
    @Disabled
    fun fetchAllMicroServices(){
        assertTrue(repo.findAll().size == 2)
    }


}