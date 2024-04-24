package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.service.AdvancedServiceMessage
import com.ms.itec.presentation.mapper.FromDto
import org.springframework.stereotype.Service

@Service
class AdvancedServiceMessageImpl(private val advancedServiceMessage: AdvancedServiceMessage){
    fun sendMessage(prospectModelDto: ProspectModelDto){
        val messageAdvancedDto = FromDto().toMessageAdvancedDto(prospectModelDto)
        print(advancedServiceMessage.sendMessage(messageAdvancedDto))
    }

}