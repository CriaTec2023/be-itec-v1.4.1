package com.ms.itec.application.dto.response

import lombok.Builder

@Builder
data class OpinionResponseDto(
    var name:String = "",
    var email:String = "",
    var polo:String = "",
    var phone:String = "",
    var course:String = "",
    var message: String = "",
    var lgpd:Boolean = false,
    var createdAt:String= ""

)
