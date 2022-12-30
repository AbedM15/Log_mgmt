package com.abc.logManagement.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "micro_services_logs")
data class Log (

    @Id
    @Column(name = "log_id")
    var logId:Long,

    @Column(name = "log_level",nullable = false)
    var level:String,

    @Column(name = "resolution")
    var resolution:String,

    @Column(name = "log",nullable = false)
    var log:String,

    @Column(name = "time",nullable = false)
    var time: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "micro_service_id")
    var microService:MicroService

    )