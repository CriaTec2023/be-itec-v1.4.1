package com.ms.itec.domain.entity.opnions

import jakarta.persistence.*
import java.time.LocalDateTime


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

