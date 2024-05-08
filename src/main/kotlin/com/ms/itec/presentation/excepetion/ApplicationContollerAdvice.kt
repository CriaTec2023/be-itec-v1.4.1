package com.ms.itec.presentation.excepetion

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApplicationContollerAdvice {

    @ExceptionHandler(OperationNotCompletedException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleOperationNotComplete(ex: OperationNotCompletedException):Map<String, String> {
        val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
        logger.error("Operation Not Completed: ${ex.message}")
        return mapOf("Operation Not Completed:" to ex.message!!)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleValidationExceptions(ex: IllegalArgumentException): Map<String, String> {
        val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
        logger.error("Operation Not Completed: ${ex.message}")
        return mapOf("Invalid Arguments passed in parameters:" to ex.message!!)
    }

    @ExceptionHandler(RecordNotFoundException::class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    fun handleRecordNotfound(ex: OperationNotCompletedException): Map<String, String> {
        val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
        logger.error("Operation Not Completed: ${ex.message}")
        return mapOf("Record Not Found" to ex.message!!)
    }

    @ExceptionHandler(NotPossibleSendTheMessageForNumbers::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun notPossibleSendTheMessageForNumbers(ex: OperationNotCompletedException): Map<String, String> {
        val logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
        logger.error("Operation Not Completed: ${ex.message}")
        return mapOf("Is not possible send the messages" to ex.message!!)
    }
}