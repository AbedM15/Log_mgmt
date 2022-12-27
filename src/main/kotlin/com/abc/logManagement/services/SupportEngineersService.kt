package com.abc.logManagement.services

import com.abc.logManagement.entities.SupportEngineer

interface SupportEngineersService {
     fun addSupportEngineer(supportEngineer: SupportEngineer): SupportEngineer
     fun deleteSupportEngineerById(id: Long)

     fun deleteSupportEngineerByEmail(email: String)
     fun getAll(): List<SupportEngineer>
     fun getSupportEngineerById(id: Long): SupportEngineer

     fun getSupportEngineerByEmail(email: String): SupportEngineer
}