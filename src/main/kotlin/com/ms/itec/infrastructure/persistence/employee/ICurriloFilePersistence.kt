package com.ms.itec.infrastructure.persistence.employee

import com.ms.itec.domain.entity.employee.CurriculoFile
import com.ms.itec.domain.repository.employee.ICurriloFileRepository
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Transactional
interface ICurriloFilePersistence: JpaRepository<CurriculoFile, String>, ICurriloFileRepository {

    override fun saveCurriculoFile(file: CurriculoFile): String {
        return save(file).name
    }

    override fun deleteCurriculoFile() {
        deleteAll()
    }

    override fun findCurriculoFileByLink(link: String): CurriculoFile {
        return findById(link).get()
    }
}