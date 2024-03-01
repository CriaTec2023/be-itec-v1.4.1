package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.OpinionDto
import com.ms.itec.application.dto.response.OpinionResponseDto
import com.ms.itec.application.service.IOpinionsService
import com.ms.itec.domain.entity.opnions.OpinionsModel
import com.ms.itec.infrastructure.persistence.IOpinionsPersistence
import com.ms.itec.presentation.excepetion.OperationNotCompletedException
import com.ms.itec.presentation.mapper.FromDto
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class OpinionServiceImpl(private val persistence:IOpinionsPersistence): IOpinionsService {

    @Transactional
    override fun saveOpinion(opnionDto: OpinionDto): OpinionResponseDto {
        val opinion:OpinionsModel  = FromDto().toEntity(opnionDto)

        try {
            val savedOpinion = persistence.save(opinion)
            return OpinionResponseDto(
                email = savedOpinion.email,
                message = savedOpinion.message,
                name = savedOpinion.name,
                phone = savedOpinion.phone,
                polo = savedOpinion.polo,
                course = savedOpinion.course,
                lgpd = savedOpinion.lgpd,
                createdAt = savedOpinion.createdAt
            )

        } catch (e: OperationNotCompletedException) {
            throw OperationNotCompletedException(e.message.toString())
        }
    }

    override fun getOpinionsPaginatedfun(pageable: Pageable): Page<OpinionsModel> {
        return try {
            persistence.findAll(pageable)
        } catch (e: OperationNotCompletedException) {
            throw OperationNotCompletedException(e.message.toString())
        }
    }
}