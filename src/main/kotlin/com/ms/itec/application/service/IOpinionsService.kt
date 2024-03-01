package com.ms.itec.application.service

import com.ms.itec.application.dto.request.OpinionDto
import com.ms.itec.application.dto.response.OpinionResponseDto
import com.ms.itec.domain.entity.opnions.OpinionsModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IOpinionsService {

    fun saveOpinion(opnion: OpinionDto): OpinionResponseDto

    fun getOpinionsPaginatedfun(pageable: Pageable): Page<OpinionsModel>

}