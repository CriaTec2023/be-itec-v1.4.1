package com.ms.itec.repositories

import com.ms.itec.entity.Forms
import com.ms.itec.enums.Polos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FormsRepository: JpaRepository<Forms, String> {

    fun getAllByPolo(polo: Polos): List<Forms>;
}