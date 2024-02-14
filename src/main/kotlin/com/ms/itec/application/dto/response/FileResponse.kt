package com.ms.itec.application.dto.response

data class FileResponse(
    val id : String,
    val name : String,
    val contentType : String,
    val link : String,
    val size : Long
)
