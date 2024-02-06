package com.ms.itec.domain.entity.employee

import java.io.File

data class EmployeeModel(

    var name:String,
    var email:String,
    var polo:String,
    var  phone:String,
    var setor:String,
    var  curriculoFile: File,
    var lgpd:Boolean

)
