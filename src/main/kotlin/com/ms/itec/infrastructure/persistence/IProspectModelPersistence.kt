package com.ms.itec.infrastructure.persistence

import com.ms.itec.domain.repository.ProspectModelRepository
import com.ms.itec.domain.entity.ProspectModel
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
@Transactional
interface IProspectModelPersistence: JpaRepository<ProspectModel, String>, ProspectModelRepository{

        @Query("SELECT p FROM ProspectModel p WHERE p.ownerId = ''")
        override fun getWithoutOwner(): Optional<List<ProspectModel>>

        @Query("SELECT p FROM ProspectModel p WHERE p.ownerId = :ownerId")
        override fun getWithIdOwner(@Param("ownerId")id:String): Optional<List<ProspectModel>>

//        fun getByOwnerIdEmpty():Optional<List<ProspectModel>>

//        fun getByOwnerIdEquals(ownerId: String): Optional<List<ProspectModel>>

}