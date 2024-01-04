package com.ms.itec.presentation.mapper

import com.ms.itec.application.dto.request.ContentDto
import com.ms.itec.application.enums.Tag
import com.ms.itec.entity.Content
import jakarta.validation.Valid
import java.util.UUID

class FromDto {
    fun toEntity(@Valid dto: ContentDto): Content{

        return Content(
            id = UUID.randomUUID().toString(),
            title = dto.title,
            description = dto.description,
            content = dto.content,
            background = dto.background,
            tag = Tag.valueOf(dto.tag),
            avgSalary = dto.avgSalary
        )
    }}

