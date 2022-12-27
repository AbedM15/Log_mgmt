package com.abc.logManagement.services

import com.abc.logManagement.entities.SupportEngineer

interface SupportEngineersService {
     fun addSupportEngineer(supportEngineer: SupportEngineer): SupportEngineer
}