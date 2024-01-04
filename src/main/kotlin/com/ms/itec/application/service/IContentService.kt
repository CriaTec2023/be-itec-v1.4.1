package com.ms.itec.application.service

import com.ms.itec.application.dto.request.ContentDto
import com.ms.itec.application.dto.request.ContentDtoWithId
import com.ms.itec.entity.Content


interface IContentService {

    fun save(contentDto: ContentDto): Content
    fun update(contentDto: ContentDtoWithId): Content
    fun getAll(): List<Content>

}