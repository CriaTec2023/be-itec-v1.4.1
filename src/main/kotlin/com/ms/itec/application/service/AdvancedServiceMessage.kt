package com.ms.itec.application.service

import com.ms.itec.application.dto.request.MessageAdvancedDto
import com.ms.itec.infrastructure.configuration.feign.FeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "advanced-service", url = "\${advanced-service.url}", configuration = [FeignConfig::class])
interface AdvancedServiceMessage {

    @PostMapping
    fun sendMessage(@RequestBody data: MessageAdvancedDto){
        print("Message sent: $data")
    }

}