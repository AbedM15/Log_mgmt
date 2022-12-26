package com.abc.logManagement.entities

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity(name = "logs")
@Table(name = "logs")
data class Log(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "log_id")
    var logId:Long,

    @ManyToOne(targetEntity = Microservice::class)
    @JoinColumn(name = "micro_service_id")
    var microServiceId:Long,

    @Column(name= "level")
    var level:String,

    @Column(name= "log")
    var log:String,

    @Column(name= "resolution")
    var resolution:String? = null,

    @Column(name= "time")
    var time: LocalDateTime
)
