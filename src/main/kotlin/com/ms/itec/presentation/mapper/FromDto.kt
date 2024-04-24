package com.ms.itec.presentation.mapper

import com.ms.itec.application.dto.request.*
import com.ms.itec.application.enums.Polos
import com.ms.itec.application.enums.Tag
import com.ms.itec.domain.entity.IdentifierProducer
import com.ms.itec.domain.entity.content.Content
import com.ms.itec.domain.entity.employee.EmployeeModel
import com.ms.itec.domain.entity.opnions.OpinionsModel
import com.ms.itec.domain.entity.prospectModel.ProspectModel
import jakarta.validation.Valid
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class FromDto {


    fun toEntity(dto: CurriculoDto, uriCurriculo: String): EmployeeModel {
        return EmployeeModel(
            id = IdentifierProducer().creatIndentification(),
            name = dto.name.trim(),
            email = dto.email.trim(),
            phone = formatBrazilianPhoneNumber(dto.phone),
            setor = dto.setor.trim(),
            polo  = dto.polo.trim(),
            education = dto.education.trim(),
            curriculoFileLink = uriCurriculo,
            lgpd = dto.lgpd,
            timeOfExperience = dto.timeOfExperience,
            createdAt = creatDate()
        )
    }

    fun toEntity(@Valid dto: ContentDto): Content {
        return Content(
            id = IdentifierProducer().creatIndentification(),
            title = dto.title.trim(),
            description = dto.description.trim(),
            content = dto.content.trim(),
            background = dto.background.trim(),
            tag = Tag.valueOf(dto.tag.trim()),
            avgSalary = dto.avgSalary
        )
    }

    fun toEntity(@Valid dto: ProspectModelDto): ProspectModel {

        return ProspectModel(
            id = IdentifierProducer().creatIndentification(),
            name = dto.name.trim(),
            email = dto.email.trim(),
            phone = formatBrazilianPhoneNumber(dto.phone),
            polo = if (dto.polo != null ) Polos.valueOf(dto.polo!!.trim()) else Polos.UNDECIDED,
            course = dto.course.trim(),
            cupom = dto.cupom,
            emailMarketing = dto.emailMarketing,
            contacted = false,
            ownerId = "",
            createdAt = creatDate(),
            updatedAt =  LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ISO_DATE_TIME)

        )

    }


    fun toEntity(@Valid dto: ProspectModelWithOwnerId): ProspectModel {

        return ProspectModel(
            id = IdentifierProducer().creatIndentification(),
            name = dto.name.trim(),
            email = dto.email.trim(),
            phone = formatBrazilianPhoneNumber(dto.phone),
            polo = if (dto.polo != null ) Polos.valueOf(dto.polo!!.trim()) else Polos.UNDECIDED,
            course = dto.course.trim(),
            cupom = dto.cupom,
            emailMarketing = dto.emailMarketing,
            contacted = false,
            ownerId = dto.ownerId,
            createdAt = LocalDateTime.now().toString(),
            updatedAt = LocalDateTime.now()
        )
    }

    fun toEntity(dto: OpinionDto): OpinionsModel {
        return OpinionsModel(
            name = dto.name.trim(),
            email = dto.email.trim(),
            polo = dto.polo.trim(),
            phone = formatBrazilianPhoneNumber(dto.phone),
            course = dto.course.trim(),
            message = dto.message.trim(),
            lgpd = dto.lgpd,
            createdAt = creatDate()
        )
    }

    fun toMessageAdvancedDto( prospectDto: ProspectModelDto): MessageAdvancedDto {
        return MessageAdvancedDto(
            body = "Olá! Bacana saber que você está interessado no curso ${prospectDto.course}, vimos pelo nosso site! Vamos seguir com o seu atendimento, responde essa mensagem para começarmos!",
            number = formatForWhatsapp(prospectDto.phone),
            queueId = getQueueId(prospectDto.polo!!),
            openTicket = "1"
        )

    }


    fun formatBrazilianPhoneNumber(input: String): String {
        // Remove todos os caracteres não numéricos
        val cleanedInput = input.replace("\\D".toRegex(), "")
        // Verifica se o número tem o formato esperado (DDD + 9 dígitos)
        return if (cleanedInput.length in 10..11) {
            // Extrai o DDD e o número de telefone
            val ddd = cleanedInput.substring(0, 2)

            val phoneNumber = cleanedInput.substring(2)
            // Formata o número
            return when (cleanedInput.length) {
                 10-> "(0$ddd) $phoneNumber" // Sem o nono dígit
                11 -> "($ddd) $phoneNumber" // Com o nono dígito
                else -> cleanedInput
            }
        } else {
            input
        }
    }

    private fun formatForWhatsapp(input: String): String {
        return buildString {
        append("55")
        append(input) }
    }



    private fun getQueueId(polo: String): String {
        return when (polo) {
            "VR-VILA" -> "12"
            "VR-RETIRO" -> "12"
            "RESENDE" -> "6"
            "ANGRA DOS REIS" -> "1"
            "PORTO REAL" -> "5"
            "ITATIAIA" -> "4"
            "BARRA MANSA" -> "3"
            else -> "1"
        }
    }

    private fun creatDate(): String {
        val brazil = Locale("pt", "BR")
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").withLocale(brazil))
    }
}

