package com.ms.itec.repositories

import com.ms.itec.entity.course.Course
import com.ms.itec.enums.TagCourse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository:JpaRepository<Course, String> {

    fun findByTitle(title: String): Course?;

    fun findByTag(tag: TagCourse): List<Course>;


}