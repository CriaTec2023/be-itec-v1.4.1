package com.ms.itec.domain.entity.content

import com.ms.itec.application.enums.Tag
import com.ms.itec.domain.entity.IdentifierProducer
import jakarta.persistence.*
import java.util.*

@Entity
data class Content(
    @Id
    var id: String = IdentifierProducer().creatIndentification(),
    @Enumerated(EnumType.STRING)
    var tag: Tag,
    var title: String = "",
    var description: String = "",
    var content: String = "",
    var background: String = "",
    var avgSalary: Double = 0.0,
)
