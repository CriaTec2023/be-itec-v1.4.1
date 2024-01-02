package com.ms.itec.entity

import com.ms.itec.enums.Polos
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
data class Forms(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val email: String= "",
    val phone: String= "",
    val polo : Polos= Polos.Undecided,
    val course: String= "",
    val cupom: String= "",
    val emailMarketing: Boolean = false,
)
