package com.ms.itec.domain.repository

import com.ms.itec.application.enums.Tag
import com.ms.itec.domain.entity.content.Content

interface IContentRepository {
    fun findByTitle(title: String): Content?

    fun retriveByTag(tag: Tag): List<Content>
}