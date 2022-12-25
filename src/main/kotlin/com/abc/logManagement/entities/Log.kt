package com.abc.logManagement.entities

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
data class Log(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var logId:Long,

    @ManyToOne(targetEntity = Microservice::class)
    @JoinColumn(name = "microServiceId")
    var microServiceId:Long,
    var level:String,
    var log:String,
    var resolution:String? = null,
    var time: LocalDateTime
)
