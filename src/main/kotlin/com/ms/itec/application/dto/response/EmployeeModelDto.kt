package com.ms.itec.application.dto.response

data class EmployeeModelDto(
    var id: String = "",
    var name:String = "",
    var email:String = "",
    var polo:String = "",
    var phone:String = "",
    var setor:String = "",
    var curriculoFileLink: String = "",
    var lgpd:Boolean = false,
    var timeOfExperience : String
)
