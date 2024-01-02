package com.ms.itec.entity.course

import com.ms.itec.enums.Tag
import com.ms.itec.enums.TagCourse
import jakarta.persistence.*
import java.util.*
@Entity
data class Course(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = UUID.randomUUID().toString(),
    val tag: TagCourse = TagCourse.UNDEFINED,
    val title: String,
    val about: String,
    val market: String,
    val hours: String,
    val avgSalary: Double,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "course_id")
    val modules: List<CourseModule> = mutableListOf()
)
