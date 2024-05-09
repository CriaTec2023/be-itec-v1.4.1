package com.ms.itec.application.service.impl



import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.StandardCopyOption


@Service
class AdvancedServiceMessageWithImage {

    @Value("\${advanced-service.token}")
    lateinit var token: String

    @Value("\${advanced-service.url}")
    lateinit var url: String

    fun  sendMessage(img: MultipartFile, number: String, message: String, queueId: String, openTicket:String): String {

            val tempFile = Files.createTempFile("temp-file", img.originalFilename).toAbsolutePath()
            Files.copy(img.inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING)


            val restTemplate = RestTemplate()
            val headers = org.springframework.http.HttpHeaders()
            headers.contentType = MediaType.MULTIPART_FORM_DATA
            headers.setBearerAuth(token)

            val bodyMap = LinkedMultiValueMap<String, Any>()

            bodyMap.add("medias", FileSystemResource(tempFile.toString()))
            bodyMap.add("number", number)
            bodyMap.add("openTicket", openTicket)
            bodyMap.add("queueId",queueId)
            bodyMap.add("body", message.toByteArray(StandardCharsets.UTF_8))

            val requestEntity = HttpEntity(bodyMap, headers)

            val responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String::class.java
            )

            Files.deleteIfExists(tempFile)

            return responseEntity.body ?: "Empty response"
    }
}

