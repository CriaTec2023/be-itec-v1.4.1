package com.ms.itec.application.dto.response



data class DtoResponse (
    var status: Int = 0,
    var sucess: Boolean = false,
    var error: String = ""
)