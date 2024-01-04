package com.ms.itec.domain.repository

import com.ms.itec.application.enums.Tag
import com.ms.itec.entity.Content

interface ContentRepository {



    fun findByTitle(title: String): Content?

    fun findByTag(tag: Tag): List<Content>
}