package com.ms.itec.controllers

import com.ms.itec.entity.Content
import com.ms.itec.service.impl.ContentServiceImpl
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("content")
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