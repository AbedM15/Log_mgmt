package com.abc.logManagement.repositories

import com.abc.logManagement.entities.Log
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LogsRepository:JpaRepository<Log,Long> {

   fun findByLevel(level:String):MutableList<Log>?

   fun findByResolution(resolution:String):MutableList<Log>?



}