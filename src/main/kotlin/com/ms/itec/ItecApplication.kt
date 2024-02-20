package com.ms.itec

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ItecApplication

fun main(args: Array<String>) {
    runApplication<ItecApplication>(*args)
}
