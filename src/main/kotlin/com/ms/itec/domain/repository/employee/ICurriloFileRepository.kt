package com.ms.itec.domain.repository.employee

import com.ms.itec.domain.entity.employee.CurriculoFile


interface ICurriloFileRepository{
    fun saveCurriculoFile(file: CurriculoFile): String
    fun deleteCurriculoFile()
    fun findCurriculoFileByLink(link: String): CurriculoFile
}
