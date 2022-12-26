package com.abc.logManagement.repositories

import com.abc.logManagement.entities.Microservice
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MicroServicesRepository: JpaRepository<Microservice,Long> {

    fun findByMicroServiceNameIgnoreCase(microServiceName:String):Microservice?

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM micro_services  WHERE micro_service_name = :name", nativeQuery = true)
    fun deleteByMicroServiceName(name:String)


}