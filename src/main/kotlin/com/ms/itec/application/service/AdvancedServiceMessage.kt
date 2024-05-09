package com.ms.itec.application.service

import com.ms.itec.application.dto.request.MessageAdvancedDto
import com.ms.itec.infrastructure.configuration.feign.FeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@FeignClient(name = "advanced-service", url = "\${advanced-service.url}", configuration = [FeignConfig::class])
interface AdvancedServiceMessage {

    @RequestMapping(method = [RequestMethod.POST], consumes = ["application/json"])
    fun sendMessage(@RequestBody data: MessageAdvancedDto)


}