package com.abc.logManagement.repositories

import com.abc.logManagement.entities.SupportEngineer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SupportEngineersRepository:JpaRepository<SupportEngineer,Long> {

    @Query("SELECT COUNT(support_engineer_id) FROM support_engineers WHERE email_address = :email ",nativeQuery = true)
    fun findEngineerByEmail(email:String):Int


}