package com.ms.itec.application.dto.request

import org.springframework.web.multipart.MultipartFile


data class MessageWithImageAdvancedDto (
    var medias: MultipartFile,
    var number: String,
    var openTicket:String,
    var queueId:String,
    var body:String,
)
