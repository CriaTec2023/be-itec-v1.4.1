package com.ms.itec.application.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ProspectModelWithIdDto(
    @field:NotBlank(message = "O campo id é obrigatório")
    val id: String = "",
    @field:NotBlank(message = "O campo nome é obrigatório")
    var name: String = "",
    @field:Email(message = "O campo email é obrigatório")
    var email: String= "",
    @field:NotBlank(message = "O campo telefone é obrigatório")
    var phone: String= "",
    @field:Pattern(
        regexp = "^(VR-VILA|VR-RETIRO|RESENDE|ANGRA DOS REIS|PORTO REAL|ITATIAIA|BARRA MANSA)$",
        message = "O campo tag deve ser VR-VILA, VR-RETIRO, RESENDE, ANGRA DOS REIS, PORTO REAL, ITATIAIA OU BARRA MANSA"
    )
    var polo : String?,
    @field:NotBlank(message = "O campo curso é obrigatório")
    var course: String = "",
    var cupom: String = "",
    var emailMarketing: Boolean = false,

    )
