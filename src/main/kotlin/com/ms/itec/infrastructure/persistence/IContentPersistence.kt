package com.ms.itec.infrastructure.persistence

import com.ms.itec.application.enums.Tag
import com.ms.itec.domain.repository.IContentRepository
import com.ms.itec.domain.entity.content.Content
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface IContentPersistence: JpaRepository<Content, String>, IContentRepository{

    @Query("SELECT c FROM Content c WHERE c.title = :title")
    override fun findByTitle(@Param("title") title: String): Content?
    @Query("SELECT c FROM Content c WHERE c.tag = :tag")
    override fun retriveByTag(@Param("tag") tag: Tag): List<Content>
}