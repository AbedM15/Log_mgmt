package com.abc.logManagement.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity(name = "support_engineers")
@Table(name = "support_engineers")
data class SupportEngineer (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "support_engineer_id")
    var supportEngineerId:Long,
    @Column(name = "first_name")
    var firstName:String,
    @Column(name = "last_name")
    var lastName:String,
    @Column(name = "email_address",unique = true)
    var emailAddress:String,

    @OneToMany(targetEntity = EngineersMicroServicesAllocations::class)
    @JoinColumn(name = "support_engineer_id", referencedColumnName = "support_engineer_id")
    var engineersMicroServiceAllocations: List<EngineersMicroServicesAllocations>?


)