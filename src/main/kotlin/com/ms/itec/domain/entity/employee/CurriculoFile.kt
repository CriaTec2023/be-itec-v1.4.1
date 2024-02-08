package com.ms.itec.domain.entity.employee

import com.ms.itec.domain.entity.IdentifierProducer
import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.EqualsAndHashCode

@EqualsAndHashCode
@Entity
data class CurriculoFile(
    @Id
    var id: String = IdentifierProducer().creatIndentification(),
    var name: String,
    var link: String,
    var byte: ByteArray,


    )
