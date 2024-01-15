package com.ms.itec.presentation.controller

import com.ms.itec.application.dto.request.ContentDto
import com.ms.itec.application.dto.request.ContentDtoWithId
import com.ms.itec.application.dto.response.ResponseUpdateAndSaveDto
import com.ms.itec.application.service.impl.ContentServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("v1/contents")
class ContentController(private val contentService: ContentServiceImpl) {

    private val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/recordContent")
    fun save(@RequestBody contentDto: ContentDto):ResponseEntity<ResponseUpdateAndSaveDto> {
       val response =  contentService.save(contentDto)
        logger.info("Content saved: ${response.title} with id: ${response.id}")
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseUpdateAndSaveDto( "'SUCCESSFULLY SAVED':", "Content: ${response.title} created with id: ${response.id}")
        )
    }

    @PostMapping("/update")
    fun update(@RequestBody contentDto: ContentDtoWithId):ResponseEntity<ResponseUpdateAndSaveDto> {
        val response =  contentService.update(contentDto)
        logger.info("Content Update: ${response.title} with id: ${response.id}")
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseUpdateAndSaveDto( "'SUCCESSFULLY UPDATED':","Content Update: ${response.title} update content with id: ${response.id}")
        )
    }

    @GetMapping("/allRecords")
    fun getAll(): ResponseEntity<Any> {
        val response =  contentService.getAll()
        if (response.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO CONTENT")
        }
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<ResponseUpdateAndSaveDto> {
        contentService.delete(id)
        logger.info("Content delete $id")

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseUpdateAndSaveDto( "'SUCCESSFULLY DELETED':","Content deleted with id: $id")
        )
    }
}