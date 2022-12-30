package com.abc.logManagement.entities

import jakarta.persistence.*

@Entity
@Table(name = "micro_services")
data class MicroService (

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "micro_service_id")
    var microServiceId:Long? = 1,

    @Column(name = "micro_service_name",unique = true,nullable = false)
    var microServiceName:String,


    @OneToMany(mappedBy = "microService")
    var microServiceLogs:MutableList<Log>? = null

)