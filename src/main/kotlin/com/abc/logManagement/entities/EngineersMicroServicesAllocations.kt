package com.abc.logManagement.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity(name ="engineers_micro_services_allocations")
@Table(name = "engineers_micro_services_allocations")
data class EngineersMicroServicesAllocations(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "allocation_id")
    var allocationId: Long,

    @ManyToOne(targetEntity = SupportEngineer::class)
    @JoinColumn(name = "support_engineer_id")
    var supportEngineerId: Long,

    @ManyToOne(targetEntity = Microservice::class)
    @JoinColumn(name = "micro_service_id")
    var microServiceId:Long

)
