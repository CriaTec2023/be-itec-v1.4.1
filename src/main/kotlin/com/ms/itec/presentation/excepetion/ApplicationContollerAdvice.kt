package com.ms.itec.presentation.excepetion

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApplicationContollerAdvice {

    @ExceptionHandler(OperationNotComplete::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleOperationNotComplete(ex: OperationNotComplete):Map<String, String> {
        return mapOf("Operation Not Completed:" to ex.message!!)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleValidationExceptions(ex: IllegalArgumentException): Map<String, String> {
        return mapOf("Invalid Arguments passed in parameters:" to ex.message!!)
    }

    @ExceptionHandler(RecordNotFound::class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    fun handleRecordNotfound(ex: OperationNotComplete): Map<String, String> {
        return mapOf("Record Not Found" to ex.message!!)
    }
}