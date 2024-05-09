package com.ms.itec.domain.repository

import com.ms.itec.domain.entity.prospectModel.ProspectModel
import java.util.*

interface ProspectModelRepository {

    fun getWithoutOwner(): Optional<List<ProspectModel>>

    fun getWithIdOwner(id:String): Optional<List<ProspectModel>>



}