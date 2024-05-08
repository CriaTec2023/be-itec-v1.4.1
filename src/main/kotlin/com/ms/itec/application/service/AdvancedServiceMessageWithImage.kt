package com.ms.itec.application.service


import com.ms.itec.infrastructure.configuration.feign.FeignConfigImg
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestPart


@FeignClient(name = "advanced-service-img", url = "\${advanced-service.url}", configuration = [FeignConfigImg::class])
interface AdvancedServiceMessageWithImage {

    @RequestMapping(
        method = [RequestMethod.POST],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
    )
    fun sendMessageWithImage(
        @RequestPart("number") number: String,
        @RequestPart("openTicket") openTicket: String,
        @RequestPart("queueId") queueId: String,
        @RequestPart("body") body: String,
        @RequestPart("medias") medias: ByteArray
    )

}