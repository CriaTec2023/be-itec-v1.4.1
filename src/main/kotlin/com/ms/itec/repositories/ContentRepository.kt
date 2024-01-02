package com.ms.itec.repositories

import com.ms.itec.entity.Content
import com.ms.itec.enums.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContentRepository: JpaRepository<Content, String> {

    fun findByTitle(title: String): Content?

    fun findByTag(tag: Tag): List<Content>

}