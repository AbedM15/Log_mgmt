package com.abc.logManagement.repositories

import com.abc.logManagement.entities.SupportEngineer
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface SupportEngineersRepository: JpaRepository<SupportEngineer,Long> {


    @Query(value = "SELECT COUNT(email_address)  FROM support_engineers WHERE email_address = :emailAddress", nativeQuery = true)
    fun supportEngineerExists(emailAddress:String):Int

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM support_engineers WHERE email_address = :emailAddress", nativeQuery = true)
    fun deleteSupportEngineerByEmail(emailAddress: String)



}