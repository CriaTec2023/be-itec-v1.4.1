package com.ms.itec.application.dto.response



data class ProspectResponseDto(
    var id: String = "",
    var name: String = "",
    var email: String= "",
    var phone: String= "",
    var polo :String = "",
    var course: String = "",
    var cupom: String = "",
    var emailMarketing: Boolean = false,
    var contacted: Boolean = false,
    val createdAt: String = "",
    val ownerId: String = ""
)