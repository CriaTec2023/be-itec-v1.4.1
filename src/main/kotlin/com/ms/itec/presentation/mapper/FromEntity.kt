package com.ms.itec.presentation.mapper

import com.ms.itec.application.dto.response.EmployeeModelDto
import com.ms.itec.application.dto.response.OpinionResponseDto
import com.ms.itec.application.dto.response.ProspectResponseDto
import com.ms.itec.domain.entity.employee.EmployeeModel
import com.ms.itec.domain.entity.opnions.OpinionsModel
import com.ms.itec.domain.entity.prospectModel.ProspectModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class FromEntity {

    fun toDto(prospectModel: ProspectModel): ProspectResponseDto {

        return ProspectResponseDto(
            id = prospectModel.id,
            contacted = prospectModel.contacted,
            createdAt = convertDate(prospectModel.createdAt).toString(),
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
    fun toDto(employee:EmployeeModel): EmployeeModelDto {
        return EmployeeModelDto(
            id = employee.id,
            name = employee.name,
            email = employee.email,
            polo = employee.polo,
            phone = employee.phone,
            setor = employee.setor,
            curriculoFileLink = employee.curriculoFileLink,
            lgpd = employee.lgpd,
            timeOfExperience = employee.timeOfExperience,
            createdAt = convertDate(employee.createdAt).toString()

        )
    }

    fun toDto(entity :OpinionsModel): OpinionResponseDto {
        return OpinionResponseDto(
            name = entity.name,
            email = entity.email,
            polo = entity.polo,
            phone = entity.phone,
            course = entity.course,
            message = entity.message,
            createdAt = convertDate(entity.createdAt).toString()
        )
    }

    private fun convertDate(date: String?): LocalDate {
        val brazil = Locale("pt", "BR")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").withLocale(brazil)
        val localDateTime = LocalDateTime.parse(date, formatter)
        return localDateTime.toLocalDate()
    }

}