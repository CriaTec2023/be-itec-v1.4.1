package com.ms.itec.application.service

import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.domain.entity.ProspectModel

interface IProspectModelService {

    fun save(prospectModelDto: ProspectModelDto): ProspectModel

    fun getWithOwner(ownerId: String): List<ProspectModel>

    fun getWithoutOwner(): List<ProspectModel>

    fun update(prospectModelDto: ProspectModelDto): ProspectModel

    fun delete(id: String)

    fun updateContacted(id: String): ProspectModel

}