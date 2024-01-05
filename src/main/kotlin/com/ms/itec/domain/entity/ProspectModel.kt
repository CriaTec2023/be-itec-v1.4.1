package com.ms.itec.domain.entity

import com.ms.itec.application.enums.Polos
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
data class ProspectModel(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = UUID.randomUUID().toString(),
    val ownerId: String = "",
    var name: String = "",
    var email: String= "",
    var phone: String= "",
    var polo: Polos? = Polos.UNDECIDED,
    var course: String= "",
    var cupom: String= "",
    var emailMarketing: Boolean = false,
    var contacted: Boolean = false,
)
