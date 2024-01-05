package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.service.IProspectModelService
import com.ms.itec.domain.entity.ProspectModel
import com.ms.itec.infrastructure.persistence.IProspectModelPersistence
import com.ms.itec.presentation.excepetion.OperationNotComplete
import com.ms.itec.presentation.mapper.FromDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProspectModelServiceImpl(private var prospectPersistence: IProspectModelPersistence): IProspectModelService{
    override fun save(prospectModelDto: ProspectModelDto): ProspectModel {
        val prospectModel: ProspectModel = FromDto().toEntity(prospectModelDto)
        val result = runCatching { prospectPersistence.save(prospectModel) }
        return result.getOrElse {
            throw OperationNotComplete("Error saving", it)
        }
    }




//    REFATORARNÃOCONFIAVEL
    override fun getWithOwner(ownerId: String): List<ProspectModel> {
        return runCatching {
            prospectPersistence.getWithIdOwner(ownerId)
        }.getOrElse {

            println("Erro ao obter prospects com owner ID $ownerId: ${it.message}")

            emptyList()
        } as List<ProspectModel>

    }

    override fun getWithoutOwner(): List<ProspectModel> {
        TODO("Not yet implemented")
    }

    override fun update(prospectModelDto: ProspectModelDto): ProspectModel {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override fun updateContacted(id: String): ProspectModel {
        TODO("Not yet implemented")
    }

}
