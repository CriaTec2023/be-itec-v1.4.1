package com.ms.itec.domain.prospectModel

import com.ms.itec.application.enums.Polos
import com.ms.itec.domain.entity.IdentifierProducer
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime


@Entity
data class ProspectModel(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = IdentifierProducer().creatIndentification(),
    var ownerId: String = "",
    var name: String = "",
    var email: String= "",
    var phone: String= "",
    var polo: Polos? = Polos.UNDECIDED,
    var course: String= "",
    var cupom: String= "",
    var emailMarketing: Boolean = false,
    var contacted: Boolean = false,
    var createdAt: String = LocalDateTime.now().toString(),
    var updatedAt: LocalDateTime = LocalDateTime.now()

    )
