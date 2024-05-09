package com.ms.itec.presentation.controller

import com.ms.itec.application.dto.request.OpinionDto
import com.ms.itec.application.dto.response.DtoResponse
import com.ms.itec.application.service.impl.OpinionServiceImpl
import com.ms.itec.presentation.mapper.FromEntity
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/opinions")
class OpinionController(val opinionService: OpinionServiceImpl) {


    @PostMapping("/creatOpinion")
    fun createOpinion(@RequestBody opinion: OpinionDto): ResponseEntity<Any> {
        return try {
            opinionService.saveOpinion(opinion)
            val response = DtoResponse(
                status = 201,
                success = true,
                error = "",
            )
            ResponseEntity.status(HttpStatus.CREATED).body(response)

        }catch (e: Exception){
            val response = DtoResponse(
                status = 400,
                success = false,
                error = e.message!!.toString(),
            )
            ResponseEntity.badRequest().body(response)
        }
    }

    @GetMapping("/getOpinions")
    fun getOpinions(pageable: Pageable): ResponseEntity<Any> {
        return try {
            val opinionsPage = opinionService.getOpinionsPaginatedfun(pageable)
            val sortedOpinions = opinionsPage.sortedBy { it.createdAt } // Ordena os resultados pela data
                .map { FromEntity().toDto(it) } // Converte para dto
            val page = PageImpl(sortedOpinions, opinionsPage.pageable, opinionsPage.totalElements)
            ResponseEntity.ok().body(page)

        }catch (e: Exception){
            ResponseEntity.badRequest().body(e.message)
        }
    }

}