package com.abc.logManagement.entities

import jakarta.persistence.*

@Entity
@Table(name = "support_engineers")
data class SupportEngineer (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_engineer_id")
    var id:Long?,

    @Column(name = "first_name", nullable = false)
    var firstName:String,

    @Column(name = "last_name", nullable = false)
    var lastName:String,

    @Column(name = "email_address", nullable = false)
    var emailAddress:String,


    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "engineer_micro_service",
    joinColumns = [JoinColumn(name = "support_engineer_id", referencedColumnName = "support_engineer_id" )
    ],
    inverseJoinColumns = [JoinColumn(name = "micro_service_id", referencedColumnName = "micro_service_id")])
    var microServices:MutableSet<MicroService>?



        )
