package com.abc.logManagement.repositories

import com.abc.logManagement.entities.Microservice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MicroServicesRepository: JpaRepository<Microservice,Long> {

    fun findByMicroServiceNameIgnoreCase(microServiceName:String):Microservice?
}