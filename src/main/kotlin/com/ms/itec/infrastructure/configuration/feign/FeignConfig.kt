package com.ms.itec.infrastructure.configuration.feign

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig : RequestInterceptor {

    override fun apply(template: RequestTemplate?) {
        template?.let {
            it.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJpYXQiOjE1MTYyMzkwMjJ9.1J7")
            it.header("Content-Type", "application/json")
        }
    }

}