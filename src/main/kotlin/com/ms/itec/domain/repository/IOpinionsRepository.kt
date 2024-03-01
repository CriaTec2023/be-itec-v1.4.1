package com.ms.itec.domain.repository

interface IOpinionsRepository {
    fun getOpinionsModelFromPolo(polo: String): List<String>
}