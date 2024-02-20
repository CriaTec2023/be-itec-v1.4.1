package com.ms.itec.application.service

import com.ms.itec.application.dto.response.ResponseAuthDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader



@FeignClient(name = "auth-service", url = "\${auth-service.url}")
interface AuthService {

  @GetMapping
  fun validateToken(@RequestHeader("Authorization") token: String): ResponseAuthDto
}
