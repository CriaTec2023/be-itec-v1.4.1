package com.ms.itec.application.service

import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.dto.request.ProspectModelWithIdDto
import com.ms.itec.application.dto.request.ProspectModelWithOwnerId
import com.ms.itec.domain.entity.prospectModel.ProspectModel

interface IProspectModelService {

    fun save(prospectModelDto: ProspectModelDto): ProspectModel

    fun getWithOwner(idOwner: String): List<ProspectModel>

    fun saveWithOwner(prospectModelDto: ProspectModelWithOwnerId): ProspectModel

    fun getWithoutOwner(): List<ProspectModel>

    fun update(prospectModelDto: ProspectModelWithIdDto): ProspectModel

    fun delete(idProspect: String)

    fun updateContacted(idProspect: String, idOwner:String): ProspectModel


}