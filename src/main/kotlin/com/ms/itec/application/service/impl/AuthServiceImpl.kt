package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.response.ResponseAuthDto
import com.ms.itec.application.service.AuthService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthServiceImpl @Autowired constructor(private val authService: AuthService) {

    fun validateToken(token: String): Boolean {
        val validation: ResponseAuthDto = authService.validateToken(token)

        return validation.type == "Success"
    }
}