package com.ms.itec.domain.repository.employee

import com.ms.itec.domain.entity.employee.CurriculoFile


interface ICurriloFileRepository{



    fun findCurriculoFileByLink(link: String): CurriculoFile

}
