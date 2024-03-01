package com.ms.itec.domain.entity.opnions

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime
import jakarta.persistence.Column


@Entity
data class OpinionsModel(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = "",
    var name:String = "",
    var email:String = "",
    var polo:String = "",
    var phone:String = "",
    var course:String = "",
    @Column(columnDefinition = "TEXT")
    var message: String = "",
    var lgpd:Boolean = false,
    var createdAt : String = LocalDateTime.now().toString(),
)

