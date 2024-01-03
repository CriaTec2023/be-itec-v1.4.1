package com.ms.itec.application.service

import com.ms.itec.entity.Content


interface IContentService {

    fun save(content: Content): Content
    fun update(content: Content): Content
    fun getAll(): List<Content>
    fun getById(id: String): Content?
}