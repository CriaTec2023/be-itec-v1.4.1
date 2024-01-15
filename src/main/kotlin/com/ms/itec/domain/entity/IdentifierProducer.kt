package com.ms.itec.domain.entity

import java.util.*

data class IdentifierProducer(
    var identification : String = "",
){
    fun creatIndentification(): String{
        return UUID.randomUUID().toString()
    }
}
