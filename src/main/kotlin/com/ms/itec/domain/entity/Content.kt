package com.ms.itec.entity

import com.ms.itec.application.enums.Tag
import jakarta.persistence.*
import java.util.*

@Entity
data class Content(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = UUID.randomUUID().toString(),
    @Enumerated(EnumType.STRING)
    var tag: Tag,
    var title: String = "",
    var description: String = "",
    var content: String = "",
    var background: String = "",
    var avgSalary: Double = 0.0,
)
