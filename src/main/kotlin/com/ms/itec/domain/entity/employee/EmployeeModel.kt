package com.ms.itec.domain.entity.employee

import com.ms.itec.domain.entity.IdentifierProducer
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class EmployeeModel(
    @Id
    var id: String = IdentifierProducer().creatIndentification(),
    var name:String = "",
    var email:String = "",
    var polo:String = "",
    var phone:String = "",
    var setor:String = "",
    var curriculoFileLink: String = "",
    var lgpd:Boolean = false,
    var timeOfExperience : String

)
