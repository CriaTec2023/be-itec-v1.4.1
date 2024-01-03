package com.ms.itec.application.dto.request

import com.ms.itec.application.enums.Tag
import com.ms.itec.entity.Content
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.PositiveOrZero

data class ContentDto(
    @field:NotBlank(message = "O campo título é obrigatório")
    val title: String,

    @field:NotBlank(message = "O campo descrição é obrigatório")
    val description: String,

    @field:NotBlank(message = "O campo conteúdo é obrigatório")
    val content: String,

    @field:NotBlank(message = "O campo background é obrigatório")
    val background: String,

    @field:NotNull(message = "O campo tag é obrigatório")
    @field:Pattern(
        regexp = "^(NEWS|BLOG|POST)$",
        message = "O campo tag deve ser NEWS, BLOG ou POST"
    )
    val tag: String,

    @field:PositiveOrZero(message = "O campo salário médio precisa ser maior ou igual a zero")
    val avgSalary: Double,
)
