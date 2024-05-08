package com.ms.itec.application.dto.request

import org.springframework.web.multipart.MultipartFile

data class ListOfNumbersImageAndText(
    var text: String,
    var image: MultipartFile,
    var numbers: List<String>
)
