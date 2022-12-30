package com.abc.logManagement.services

import com.abc.logManagement.repositories.LogsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LogServiceImpl:LogService {

    @Autowired
    lateinit var repo:LogsRepository
}