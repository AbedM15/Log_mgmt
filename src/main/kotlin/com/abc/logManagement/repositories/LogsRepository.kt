package com.abc.logManagement.repositories

import com.abc.logManagement.entities.Log
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LogsRepository:JpaRepository<Log,Long> {
}