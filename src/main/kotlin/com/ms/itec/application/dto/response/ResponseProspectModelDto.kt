package com.ms.itec.application.dto.response

import com.ms.itec.application.enums.Polos


data class ResponseProspectModelDto(
    var id: String = "",
    var ownerId : String = "",
    var name: String = "",
    var email: String= "",
    var phone: String= "",
    var polo : Polos = Polos.UNDECIDED,
    var course: String = "",
    var cupom: String? = "",
    var emailMarketing: Boolean = false,
    var contacted : Boolean = false,
    )
