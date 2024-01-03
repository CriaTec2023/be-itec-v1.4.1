package com.ms.itec.entity.course

import jakarta.persistence.*
import java.util.*

@Entity
data class CourseModule(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    @ElementCollection
    val topics: List<String> = emptyList()
)

