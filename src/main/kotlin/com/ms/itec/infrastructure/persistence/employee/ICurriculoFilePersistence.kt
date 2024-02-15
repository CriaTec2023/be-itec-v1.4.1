package com.ms.itec.infrastructure.persistence.employee

import com.ms.itec.domain.entity.employee.CurriculoFile
import com.ms.itec.domain.repository.employee.ICurriloFileRepository
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Transactional
interface ICurriculoFilePersistence: JpaRepository<CurriculoFile, String>, ICurriloFileRepository {


    override fun findCurriculoFileByLink(link: String): CurriculoFile {
        return findById(link).get()
    }
}