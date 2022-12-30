package com.abc.logManagement.services

import com.abc.logManagement.dto.AllEngineers
import com.abc.logManagement.dto.SupportEngineerCreate
import com.abc.logManagement.dto.SupportEngineerCreated
import com.abc.logManagement.dto.SupportEngineerRetrieved

interface SupportEngineerService {
     fun createSupportEngineer(supportEngineer: SupportEngineerCreate): SupportEngineerCreated
     fun getAllEngineers(): MutableList<AllEngineers>
     fun retrieveEngineerById(id: Long): SupportEngineerRetrieved
}