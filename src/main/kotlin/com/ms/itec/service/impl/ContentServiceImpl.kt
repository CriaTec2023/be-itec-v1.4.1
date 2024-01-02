package com.ms.itec.service.impl

import com.ms.itec.entity.Content
import com.ms.itec.repositories.ContentRepository
import com.ms.itec.service.IContentService
import org.springframework.stereotype.Service

@Service
class ContentServiceImpl(private val contentRepository: ContentRepository): IContentService {

    override fun save(content: Content): Content {
        return contentRepository.save(content)
    }

    override fun update(content: Content): Content {
        return contentRepository.save(content)
    }

    override fun getAll(): List<Content> {
        return contentRepository.findAll()
    }

    override fun getById(id: String): Content? {
        return contentRepository.findById(id).orElse(null)
    }
}