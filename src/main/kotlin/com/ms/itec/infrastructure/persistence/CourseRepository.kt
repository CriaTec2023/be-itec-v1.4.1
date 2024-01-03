package com.ms.itec.infrastructure.persistence

import com.ms.itec.application.enums.TagCourse
import com.ms.itec.entity.course.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository:JpaRepository<Course, String> {

    fun findByTitle(title: String): Course?;

    fun findByTag(tag: TagCourse): List<Course>;


}