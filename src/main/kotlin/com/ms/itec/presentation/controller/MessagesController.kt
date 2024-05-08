package com.ms.itec.presentation.controller

import com.ms.itec.application.dto.request.ListOfNumbersImageAndText
import com.ms.itec.application.dto.request.MessageWithImageAdvancedDto
import com.ms.itec.application.service.impl.AdvancedServiceMessageImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("v1/message")
class MessagesController(private val messageService: AdvancedServiceMessageImpl) {

    @PostMapping("sendMessage/img", consumes = ["multipart/form-data"])
    fun sendMessage(
        @RequestParam("img") img: MultipartFile,
        @RequestParam("text") text: String,
        @RequestParam("listNumber")listNumber: List<String>

    ): ResponseEntity<MutableList<MessageWithImageAdvancedDto>> {

        print(text)
        print(listNumber)

        val list = ListOfNumbersImageAndText(
            text,
            img,
            listNumber
        )

        val listResponse = messageService.sendMessageWithImage(list)


        return ResponseEntity.status(HttpStatus.OK).body(listResponse)



    }

}