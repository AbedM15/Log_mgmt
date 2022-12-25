package com.abc.logManagement.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class EngineersMicroServicesAllocations(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var allocationId: Long,

    @ManyToOne(targetEntity = SupportEngineer::class)
    @JoinColumn(name = "supportEngineerId")
    var supportEngineerId: Long,

    @ManyToOne(targetEntity = Microservice::class)
    @JoinColumn(name = "microServiceId")
    var microServiceId:Long

)
