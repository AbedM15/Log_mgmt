package com.abc.logManagement.repositories

import com.abc.logManagement.entities.MicroService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MicroServicesRepository : JpaRepository<MicroService,Long>{

    @Query(value = "SELECT COUNT(micro_service_id) FROM micro_services WHERE micro_service_name = :microServiceName",nativeQuery = true)
    fun findMicroServiceByName(microServiceName:String):Int

    @Query(value = "SELECT support_engineer_id FROM engineer_micro_service WHERE micro_service_id = :microServiceId", nativeQuery = true)
    fun findSupportEngineerIdsByMicroServiceId(microServiceId: Long): List<Long>?




}