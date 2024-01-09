package com.ms.itec.presentation.mapper

import com.ms.itec.application.dto.response.ProspectResponseDto
import com.ms.itec.domain.entity.ProspectModel

class FromEntity {

    fun toDto(prospectModel: ProspectModel): ProspectResponseDto {

        return ProspectResponseDto(
            id = prospectModel.id,
            contacted = prospectModel.contacted,
            createdAt = prospectModel.createdAt.toString(),
            name = prospectModel.name,
            email = prospectModel.email,
            phone = prospectModel.phone,
            polo = prospectModel.polo.toString(),
            course = prospectModel.course,
            cupom = prospectModel.cupom,
            emailMarketing = prospectModel.emailMarketing

        )

    }
}