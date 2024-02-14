package com.ms.itec.application.service.employee

import com.ms.itec.domain.entity.employee.CurriculoFile
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

interface ICurriculoFileService{
    @Throws(IOException::class)
    fun saveCurriculoFile(file: MultipartFile): String
    fun deleteCurriculoFile(id: String)
    fun findCurriculoFileByLink(link: String): CurriculoFile

    fun getAllCurriculoFiles(): List<CurriculoFile>

    fun getCurriculoFileById(id: String): CurriculoFile

}
