package com.ms.itec.presentation.controller

import com.ms.itec.application.service.impl.ContentServiceImpl
import com.ms.itec.entity.Content
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("v1/content")
class ContentController(private val contentService: ContentServiceImpl) {

    @PostMapping("/save")
    fun save(@RequestBody content: Content):Content {
       return contentService.save(content)
    }

    @PostMapping("/update")
    fun update(@RequestBody content: Content) {
        contentService.update(content)
    }

    @GetMapping("/getAll")
    fun getAll(): List<Content> {
        return contentService.getAll()
    }
}