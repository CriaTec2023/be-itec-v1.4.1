package com.ms.itec.presentation.controller

import com.ms.itec.application.service.impl.AdvancedServiceMessageImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("v1/message")
class MessagesController(private val messageService: AdvancedServiceMessageImpl) {

    @PostMapping("/api/sendMessage/img", consumes = ["multipart/form-data"])
    fun sendMessage(
        @RequestPart("img") img: MultipartFile,
        @RequestParam("numbers") numbers: List<String>,
        @RequestParam("text") text: String
    ): ResponseEntity<MutableMap<String, String>> {

       val response =  messageService.sendMessageWithImage(numbers, img, text)

     return ResponseEntity.status(HttpStatus.OK).body(response)

    }

}