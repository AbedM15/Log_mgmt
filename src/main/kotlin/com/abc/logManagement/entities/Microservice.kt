package com.abc.logManagement.entities

import jakarta.persistence.*

@Entity(name = "micro_service")
@Table(name = "micro_services")
data class Microservice(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "micro_service_id" )
    var microServiceId :Long,
    @Column(name = "micro_service_name" )
    var microServiceName:String?,

    @OneToMany(targetEntity = Log::class)
    @JoinColumn(name = "micro_service_id", referencedColumnName = "micro_service_id")
    var logs: List<Log>?,

    @OneToMany(targetEntity = EngineersMicroServicesAllocations::class)
    @JoinColumn(name = "micro_service_id", referencedColumnName = "micro_service_id")
    var microServicesSupportEngineers: List<EngineersMicroServicesAllocations>?
)
