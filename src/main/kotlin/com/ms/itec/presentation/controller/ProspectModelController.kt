package com.ms.itec.presentation.controller

import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.dto.request.ProspectModelWithIdDto
import com.ms.itec.application.dto.response.ResponseUpdateAndSaveDto
import com.ms.itec.application.service.impl.ProspectModelServiceImpl
import com.ms.itec.domain.entity.ProspectModel
import com.ms.itec.presentation.mapper.FromEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1/prospects")
class ProspectModelController(private val prospectService: ProspectModelServiceImpl) {

    @PostMapping("/recordProspect")
    fun save(@RequestBody prospectDto: ProspectModelDto): ResponseEntity<ResponseUpdateAndSaveDto> {
       val response =  prospectService.save(prospectDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ResponseUpdateAndSaveDto( "'SUCCESSFULLY SAVED':", "Prospect: ${response.name} created with id: ${response.id}")
        )
    }

    @PostMapping("/update")
    fun update(@RequestBody prospectDto: ProspectModelWithIdDto):ResponseEntity<ResponseUpdateAndSaveDto> {
        val response =  prospectService.update(prospectDto)
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseUpdateAndSaveDto( "'SUCCESSFULLY UPDATED':","Prospect Update: ${response.name} update prospect with id: ${response.id}")
        )
    }

    @GetMapping("/records")
    fun getRecordsWithoutOwner(): ResponseEntity<Any> {
        val data =  prospectService.getWithoutOwner()
        if (data.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO CONTENT")
        }

        val responseData = data.asSequence()
            .map { FromEntity().toDto(it) }
            .sortedByDescending { it.createdAt }
            .toList()

        return ResponseEntity.status(HttpStatus.OK).body(responseData)
    }
    @GetMapping("/records/{key}")
    fun getRecordsWithOwner(@PathVariable key:String): ResponseEntity<Any> {
        val response =  prospectService.getWithOwner(key)
        if (response.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO CONTENT")
        }
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<ResponseUpdateAndSaveDto> {
        prospectService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseUpdateAndSaveDto( "'SUCCESSFULLY DELETED':","Prospect deleted with id: $id")
        )
    }

    @PostMapping("/saveMulti")
    fun saveMulti(@RequestBody prospectDto: List<ProspectModelDto>): ResponseEntity<List<ProspectModel>> {

        prospectDto.forEach {
            prospectService.save(it)
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(
            prospectDto.map { prospectService.save(it) }
        )
    }
}