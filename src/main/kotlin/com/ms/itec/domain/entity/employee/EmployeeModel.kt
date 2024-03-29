package com.ms.itec.domain.entity.employee

import com.ms.itec.domain.entity.IdentifierProducer
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class EmployeeModel(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: String = IdentifierProducer().creatIndentification(),
    var name:String = "",
    var email:String = "",
    var polo:String = "",
    var phone:String = "",
    var setor:String = "",
    var education:String = "",
    var curriculoFileLink: String = "",
    var lgpd:Boolean = false,
    var timeOfExperience : String,
    var createdAt : String = LocalDateTime.now().toString(),

    )
