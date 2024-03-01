package com.ms.itec.application.dto.request

import jakarta.validation.constraints.NotBlank


data class OpinionDto(
    @field:NotBlank(message = "O campo nome é obrigatório")
    var name:String = "",
    @field:NotBlank(message = "O campo email é obrigatório")
    var email:String = "",
    @field:NotBlank(message = "O campo polo é obrigatório")
    var polo:String = "",
    @field:NotBlank(message = "O campo telefone é obrigatório")
    var phone:String = "",
    @field:NotBlank(message = "O campo curso é obrigatório")
    var course:String = "",
    @field:NotBlank(message = "O campo mensagem é obrigatório")
    var message: String = "",
    var lgpd:Boolean = false,
)
