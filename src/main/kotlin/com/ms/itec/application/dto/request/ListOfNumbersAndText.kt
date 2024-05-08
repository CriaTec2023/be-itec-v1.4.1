package com.ms.itec.application.dto.request

import org.springframework.web.multipart.MultipartFile

data class ListOfNumbersAndText(
var text: String,
var numbers: List<String>
)
