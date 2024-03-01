package com.ms.itec.infrastructure.persistence

import com.ms.itec.domain.entity.opnions.OpinionsModel
import com.ms.itec.domain.repository.IOpinionsRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface IOpinionsPersistence: JpaRepository<OpinionsModel, String>, IOpinionsRepository {

    @Query("SELECT o.message FROM OpinionsModel o WHERE o.polo = :polo")
    override fun getOpinionsModelFromPolo(polo: String): List<String>


}