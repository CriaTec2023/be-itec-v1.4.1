package com.ms.itec.domain.repository

import com.ms.itec.domain.prospectModel.ProspectModel
import java.util.Optional

interface ProspectModelRepository {

    fun getWithoutOwner(): Optional<List<ProspectModel>>

    fun getWithIdOwner(id:String): Optional<List<ProspectModel>>



}