package com.ms.itec.application.dto.request


data class MessageWithImageAdvancedDto(
    var number: String,
    var openTicket:String,
    var queueId:String,
    var body:String,
    var image: ByteArray
)
