package com.ms.itec.domain.repository

import com.ms.itec.domain.entity.ProspectModel
import java.util.Optional

interface ProspectModelRepository {

    fun getWithoutOwner(): List<Optional<ProspectModel>>

    fun getWithIdOwner(id:String): List<Optional<ProspectModel>>



}