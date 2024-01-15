package com.ms.itec.application.service

import com.ms.itec.application.dto.request.ContentDto
import com.ms.itec.application.dto.request.ContentDtoWithId
import com.ms.itec.domain.entity.content.Content


interface IContentService {

    fun save(contentDto: ContentDto): Content
    fun update(contentDto: ContentDtoWithId): Content
    fun getAll(): List<Content>
    fun delete(id: String)
    fun retriveByTag(tagParam:String): List<Content>

}