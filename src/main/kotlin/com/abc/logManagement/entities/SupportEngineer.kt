package com.abc.logManagement.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class SupportEngineer (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var supportEngineerId:Long,
    var firstName:String,
    var lastName:String,
    var emailAddress:String


)