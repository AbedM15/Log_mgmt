package com.abc.logManagement.entities

import jakarta.persistence.*

@Entity
data class Microservice(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var microServiceId :Long,
    var microServiceName:String?,

    @OneToMany(targetEntity = Log::class)
    @JoinColumn(name = "microServiceId", referencedColumnName = "microServiceId")
    var logs: List<Log>?
)
