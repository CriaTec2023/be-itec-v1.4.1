package com.ms.itec.domain.entity.employee

import com.ms.itec.domain.entity.IdentifierProducer
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import lombok.EqualsAndHashCode

@Entity
data class CurriculoFile(
    @Id
    var id: String = IdentifierProducer().creatIndentification(),
    var name: String = "",
    var contentType: String = "",
    var link: String = "",
    @Lob
    var byte: ByteArray,


    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CurriculoFile

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
