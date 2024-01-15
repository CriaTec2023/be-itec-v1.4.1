package com.ms.itec.presentation.mapper

import com.ms.itec.application.dto.response.ProspectResponseDto
import com.ms.itec.domain.prospectModel.ProspectModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class FromEntity {

    fun toDto(prospectModel: ProspectModel): ProspectResponseDto {

        return ProspectResponseDto(
            id = prospectModel.id,
            contacted = prospectModel.contacted,
            createdAt = convertDate(prospectModel.createdAt.toString()).toString(),
            name = prospectModel.name,
            email = prospectModel.email,
            phone = prospectModel.phone,
            polo = prospectModel.polo.toString(),
            course = prospectModel.course,
            cupom = prospectModel.cupom,
            emailMarketing = prospectModel.emailMarketing,
            ownerId = prospectModel.ownerId,

        )

    }

    private fun  convertDate(date: String?): LocalDate {
        val brazil = Locale("pt", "BR")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(brazil)
        return LocalDate.parse(date, formatter)
    }
}