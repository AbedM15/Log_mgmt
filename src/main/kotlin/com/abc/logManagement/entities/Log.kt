package com.abc.logManagement.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "micro_services_logs")
data class Log (

    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var logId:Long?,

    @Column(name = "log_level",nullable = false)
    var level:String,

    @Column(name = "resolution")
    var resolution:String?,

    @Column(name = "log",nullable = false)
    var log:String,

    @Column(name = "time",nullable = false)
    var time: LocalDateTime,

    @ManyToOne(cascade = [CascadeType.PERSIST,CascadeType.MERGE])
    @JoinColumn(name = "micro_service_id")
    @JsonBackReference
    var microService:MicroService

    )