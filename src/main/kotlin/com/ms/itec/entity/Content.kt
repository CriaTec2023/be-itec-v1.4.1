package com.ms.itec.entity

import com.ms.itec.enums.Tag
import jakarta.persistence.*
import java.util.UUID

@Entity
data class Content(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = UUID.randomUUID().toString(),
    @Enumerated(EnumType.STRING)
    val tag: Tag ,
    val title: String = "",
    val description: String = "",
    val content: String = "",
    val background: String = "",
    val avgSalary: Double = 0.0,
)
