package com.ms.itec.application.service.employee.impl

import com.ms.itec.application.service.employee.ICurriculoFileService
import com.ms.itec.domain.entity.IdentifierProducer
import com.ms.itec.domain.entity.employee.CurriculoFile
import com.ms.itec.infrastructure.persistence.employee.ICurriloFilePersistence
import org.springframework.web.multipart.MultipartFile

class CurriculoFileService(private val curriculoFilePersistence: ICurriloFilePersistence) : ICurriculoFileService {

    override fun saveCurriculoFile(file: MultipartFile) {

//        val curriculoFile = CurriculoFile( IdentifierProducer().creatIndentification() , file.originalFilename!!, file.contentType, file.bytes)
//        curriculoFilePersistence.save(curriculoFile)


    }

    override fun deleteCurriculoFile(id: String) {
        curriculoFilePersistence.deleteById(id)
    }

    override fun findCurriculoFileByLink(Link: String) = curriculoFilePersistence.findById(Link).get()
}
