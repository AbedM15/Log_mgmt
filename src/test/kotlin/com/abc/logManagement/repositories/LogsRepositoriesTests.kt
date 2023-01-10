package com.abc.logManagement.repositories

import com.abc.logManagement.entities.Log
import com.abc.logManagement.entities.MicroService
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime

@DataJpaTest
class LogsRepositoriesTests {


    @Autowired
    lateinit var repo:LogsRepository



    @BeforeEach
    fun setUp(){
        repo.saveAllAndFlush(listOf(
            Log(logId = 1, level = "info", resolution = null, log = "ok",
            time = LocalDateTime.parse("2023-01-20T15:29:30"), microService = (MicroService(1,"Feedback",null,null))),
            Log(logId = 2, level = "fatal", resolution = "unresolved", log = "ok",
                time = LocalDateTime.parse("2023-01-24T15:29:30"), microService = (MicroService(1,"Feedback",null,null))),
            Log(logId = 3, level = "fatal", resolution = "resolved", log = "ok",
                time = LocalDateTime.parse("2023-01-14T15:29:30"), microService = (MicroService(1,"Feedback",null,null))),
            Log(logId = 4, level = "debug", resolution = null, log = "ok",
                time = LocalDateTime.parse("2023-01-01T15:29:30"), microService = (MicroService(1,"Feedback",null,null)))

        )

        )
    }


    @Test
    @Disabled
    fun findByLevel(){
        assertTrue(repo.findByLevel("info")!!.size==1)
    }

    @Test
    @Disabled
    fun findByResolution(){
        assertTrue(repo.findByResolution("resolved")!!.size==1)
    }

}