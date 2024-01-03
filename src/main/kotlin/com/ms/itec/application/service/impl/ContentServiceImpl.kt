package com.ms.itec.application.service.impl

import com.ms.itec.application.service.IContentService
import com.ms.itec.entity.Content
import com.ms.itec.infrastructure.persistence.ContentPersistence
import org.springframework.stereotype.Service

@Service
class ContentServiceImpl(private val contentPersistence: ContentPersistence): IContentService {

    override fun save(content: Content): Content {
        return contentPersistence.save(content)
    }

    override fun update(content: Content): Content {
        return contentPersistence.save(content)
    }

    override fun getAll(): List<Content> {
        return contentPersistence.findAll()
    }

    override fun getById(id: String): Content? {
        return contentPersistence.findById(id).orElse(null)
    }
}