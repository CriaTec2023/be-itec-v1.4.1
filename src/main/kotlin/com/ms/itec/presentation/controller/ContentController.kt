package com.ms.itec.presentation.controller

import com.ms.itec.application.dto.request.ContentDto
import com.ms.itec.application.dto.request.ContentDtoWithId
import com.ms.itec.application.dto.response.ResponseUpdateAndSaveDto
import com.ms.itec.application.service.impl.ContentServiceImpl
import com.ms.itec.entity.Content
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("v1/contents")
class ContentController(private val contentService: ContentServiceImpl) {

    @PostMapping("/recordContent")
    fun save(@RequestBody contentDto: ContentDto):ResponseEntity<ResponseUpdateAndSaveDto> {
       val response =  contentService.save(contentDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseUpdateAndSaveDto( "'SUCCESSFULLY SAVED':", "Content: ${response.title} created with id: ${response.id}")
        )
    }

    @PostMapping("/update")
    fun update(@RequestBody contentDto: ContentDtoWithId):ResponseEntity<ResponseUpdateAndSaveDto> {
        val response =  contentService.update(contentDto)
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseUpdateAndSaveDto( "'SUCCESSFULLY UPDATED':","Content Update: ${response.title} update content with id: ${response.id}")
        )
    }

    @GetMapping("/allRecords")
    fun getAll(): ResponseEntity<List<Content>> {
        val response =  contentService.getAll()
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<ResponseUpdateAndSaveDto> {
        contentService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseUpdateAndSaveDto( "'SUCCESSFULLY DELETED':","Content deleted with id: $id")
        )
    }
}