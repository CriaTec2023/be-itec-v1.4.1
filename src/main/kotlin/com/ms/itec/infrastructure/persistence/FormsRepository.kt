package com.ms.itec.infrastructure.persistence

import com.ms.itec.application.enums.Polos
import com.ms.itec.entity.Forms
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FormsRepository: JpaRepository<Forms, String> {

    fun getAllByPolo(polo: Polos): List<Forms>;
}