package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.enums.Polos
import com.ms.itec.application.service.IProspectModelService
import com.ms.itec.domain.entity.ProspectModel
import com.ms.itec.infrastructure.persistence.IProspectModelPersistence
import com.ms.itec.presentation.excepetion.OperationNotComplete
import com.ms.itec.presentation.excepetion.RecordNotFound
import com.ms.itec.presentation.mapper.FromDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProspectModelServiceImpl(private var prospectPersistence: IProspectModelPersistence): IProspectModelService{
    override fun save(prospectModelDto: ProspectModelDto): ProspectModel {
        val prospectModel: ProspectModel = FromDto().toEntity(prospectModelDto)
        return runCatching { prospectPersistence.save(prospectModel) }.getOrElse {
            throw OperationNotComplete("ERROR SAVING: ", it)
        }
    }




//    REFATORARNÃOCONFIAVEL
    override fun getWithOwner(ownerId: String): List<ProspectModel> {
        return prospectPersistence.getWithIdOwner(ownerId).orElseThrow {
                throw RecordNotFound("ERROR GETTING PROSPECTS WITH 'OWNER ID': $ownerId ")
        }
    }

    override fun getWithoutOwner(): List<ProspectModel> {
        return prospectPersistence.getWithoutOwner().orElseThrow {
            throw RecordNotFound("ERROR GETTING PROSPECTS WITHOUT 'OWNER ID' ")
        }
    }

    override fun update(prospectModelDto: ProspectModelDto): ProspectModel {
        val prospectModelForRecord = FromDto().toEntity(prospectModelDto)
        return if (!areProspectModelsEqual(prospectModelForRecord, prospectModelDto)) {
            prospectModelForRecord.apply {
                name = prospectModelDto.name
                email = prospectModelDto.email
                phone = prospectModelDto.phone
                polo = prospectModelDto.polo?.let { Polos.valueOf(it) }
                course = prospectModelDto.course
                cupom = prospectModelDto.cupom!!
                emailMarketing = prospectModelDto.emailMarketing

            }
            prospectPersistence.save(prospectModelForRecord)
        } else {
            prospectModelForRecord
        }
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override fun updateContacted(id: String): ProspectModel {
        TODO("Not yet implemented")
    }

    private fun areProspectModelsEqual(prospectModel: ProspectModel, prospectModelDto: ProspectModelDto): Boolean {
        return Objects.equals(prospectModel.name, prospectModelDto.name) &&
                Objects.equals(prospectModel.email, prospectModelDto.email) &&
                Objects.equals(prospectModel.phone, prospectModelDto.phone) &&
                Objects.equals(prospectModel.polo, prospectModelDto.polo) &&
                Objects.equals(prospectModel.phone, prospectModelDto.phone) &&
                Objects.equals(prospectModel.course, prospectModelDto.course) &&
                Objects.equals(prospectModel.cupom, prospectModelDto.cupom) &&
                Objects.equals(prospectModel.emailMarketing, prospectModelDto.emailMarketing)


    }
}
