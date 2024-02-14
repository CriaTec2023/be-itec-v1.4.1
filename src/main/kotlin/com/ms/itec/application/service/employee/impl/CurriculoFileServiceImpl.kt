package com.ms.itec.application.service.employee.impl

import com.ms.itec.application.service.employee.ICurriculoFileService
import com.ms.itec.domain.entity.IdentifierProducer
import com.ms.itec.domain.entity.employee.CurriculoFile
import com.ms.itec.infrastructure.persistence.employee.ICurriculoFilePersistence
import com.ms.itec.presentation.excepetion.OperationNotComplete
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CurriculoFileServiceImpl(private val curriculoFilePersistence: ICurriculoFilePersistence) : ICurriculoFileService {

    override fun saveCurriculoFile(file: MultipartFile) : String {
        val curriculoFile = CurriculoFile(
            id = IdentifierProducer().creatIndentification(),
            name = file.originalFilename!!,
            contentType = file.contentType!!,
            link = "",
            byte = file.bytes
        )
        return runCatching { curriculoFilePersistence.save(curriculoFile).id }.getOrElse {
            OperationNotComplete("Error saving curriculo file", it).toString()
        }

    }

    override fun deleteCurriculoFile(id: String) {
        curriculoFilePersistence.deleteById(id)
    }

    override fun findCurriculoFileByLink(link: String): CurriculoFile {
        return curriculoFilePersistence.findById(link).get()

    }

    override fun getAllCurriculoFiles(): List<CurriculoFile> {
        return curriculoFilePersistence.findAll()
    }

    override fun getCurriculoFileById(id: String): CurriculoFile {
        return curriculoFilePersistence.findById(id).get()
    }



}
