package com.ms.itec.presentation.mapper

import com.ms.itec.application.dto.request.ContentDto
import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.enums.Polos
import com.ms.itec.application.enums.Tag
import com.ms.itec.entity.Content
import com.ms.itec.domain.entity.ProspectModel
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
    }

    fun toEntity(@Valid dto: ProspectModelDto): ProspectModel {

        return ProspectModel(
            id = UUID.randomUUID().toString(),
            name = dto.name,
            email = dto.email,
            phone = dto.phone,
            polo = if (dto.polo != null ) Polos.valueOf(dto.polo!!) else Polos.UNDECIDED,
            course = dto.course,
            cupom = dto.cupom.toString(),
            emailMarketing = dto.emailMarketing,
            contacted = false,
            ownerId = ""
        )
    }
}

