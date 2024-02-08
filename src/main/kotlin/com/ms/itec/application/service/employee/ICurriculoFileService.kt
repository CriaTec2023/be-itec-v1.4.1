package com.ms.itec.application.service.employee

import com.ms.itec.domain.entity.employee.CurriculoFile
import org.springframework.web.multipart.MultipartFile

interface ICurriculoFileService{
    fun saveCurriculoFile(file: MultipartFile)
    fun deleteCurriculoFile(id: String)
    fun findCurriculoFileByLink(Link: String): CurriculoFile

}
