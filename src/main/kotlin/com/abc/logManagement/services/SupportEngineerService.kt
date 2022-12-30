package com.abc.logManagement.services

import com.abc.logManagement.dto.SupportEngineerCreate
import com.abc.logManagement.dto.SupportEngineerCreated

interface SupportEngineerService {
     fun createSupportEngineer(supportEngineer: SupportEngineerCreate): SupportEngineerCreated
}