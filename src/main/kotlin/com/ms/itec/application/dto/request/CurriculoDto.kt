package com.ms.itec.application.dto.request

import org.springframework.web.multipart.MultipartFile


data class CurriculoDto(
    var name:String = "",
    var email:String = "",
    var polo:String = "",
    var phone:String = "",
    var setor:String = "",
    var curriculoFile:MultipartFile,
    var lgpd:Boolean = false,
    var timeOfExperience : String
)
