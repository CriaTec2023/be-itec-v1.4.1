package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.dto.request.ProspectModelWithIdDto
import com.ms.itec.application.dto.request.ProspectModelWithOwnerId
import com.ms.itec.application.enums.Polos
import com.ms.itec.application.service.IProspectModelService
import com.ms.itec.domain.entity.prospectModel.ProspectModel
import com.ms.itec.infrastructure.persistence.IProspectModelPersistence
import com.ms.itec.presentation.excepetion.OperationNotComplete
import com.ms.itec.presentation.excepetion.RecordNotFound
import com.ms.itec.presentation.mapper.FromDto
import com.ms.itec.presentation.mapper.FromEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ProspectModelServiceImpl(private var prospectPersistence: IProspectModelPersistence): IProspectModelService{
    override fun save(prospectModelDto: ProspectModelDto): ProspectModel {

        val prospectModel: ProspectModel = FromDto().toEntity(prospectModelDto)

        return runCatching { prospectPersistence.save(prospectModel) }.getOrElse {
            throw OperationNotComplete("ERROR SAVING: ", it)
        }
    }

    override fun getWithOwner(idOwner: String): List<ProspectModel> {

        val listOfProspects = prospectPersistence.getWithIdOwner(idOwner).orElseThrow {
                throw RecordNotFound("ERROR GETTING PROSPECTS WITH 'OWNER ID': $idOwner ")
        }
        val listResponse = listOfProspects.sortedByDescending { it.createdAt }

        listResponse.forEach{
            FromEntity().toDto(it)
        }

        return listResponse.sortedByDescending { it.createdAt }
    }

    override fun saveWithOwner(prospectModelDto: ProspectModelWithOwnerId): ProspectModel {
        val prospectModel: ProspectModel = FromDto().toEntity(prospectModelDto)
        return runCatching { prospectPersistence.save(prospectModel) }.getOrElse {
            throw OperationNotComplete("ERROR SAVING: ", it)
        }
    }

    override fun getWithoutOwner(): List<ProspectModel> {
        val listOfProspects = prospectPersistence.getWithoutOwner().orElseThrow {
            throw RecordNotFound("ERROR GETTING PROSPECTS WITHOUT 'OWNER ID' ")
        }

        return listOfProspects.sortedByDescending { it.createdAt }
    }

    override fun update(prospectModelDto: ProspectModelWithIdDto): ProspectModel {
        val prospectModel = prospectPersistence.findById(prospectModelDto.id).orElseThrow {
            throw RecordNotFound("Record not found, id: ${prospectModelDto.id}")
        }
        println("Objeto antes do save: $prospectModelDto")

        if (!areProspectModelsEqual(prospectModel, prospectModelDto)) {
            prospectModel.name = prospectModelDto.name
            prospectModel.email = prospectModelDto.email
            prospectModel.phone = FromDto().formatBrazilianPhoneNumber(prospectModelDto.phone)
            prospectModel.polo = prospectModelDto.polo?.let { Polos.valueOf(it) }
            prospectModel.course = prospectModelDto.course
            prospectModel.cupom = prospectModelDto.cupom
            prospectModel.emailMarketing = prospectModelDto.emailMarketing
            prospectModel.updatedAt = LocalDateTime.now()
            // Salvar fora do bloco if
            prospectPersistence.save(prospectModel)
        }

        return prospectModel
    }

    override fun delete(idProspect: String) {
        val prospectRecord = prospectPersistence.findById(idProspect).orElseThrow {
            throw RecordNotFound("Record not found, id: $idProspect")
        }

        runCatching { prospectPersistence.delete(prospectRecord) }.getOrElse {
            throw OperationNotComplete("Error deleting prospect", it)
        }
    }

    override fun updateContacted(idProspect: String,idOwner:String): ProspectModel {
        val prospectRecord = prospectPersistence.findById(idProspect).orElseThrow {
            throw RecordNotFound("Record not found, id: $idProspect")
        }
        prospectRecord.apply {
            ownerId = idOwner
            contacted = true
            updatedAt = LocalDateTime.now()
        }
        return prospectPersistence.save(prospectRecord)
    }

//    override fun updateDate(): List<ProspectModel> {
//        val listOfProspects = prospectPersistence.findAll()
//
//        listOfProspects.forEach {
//            it.createdAt = "2021-08-01T00:00:00.000000"
//            prospectPersistence.save(it)
//        }
//
//        return listOfProspects
//    }

    private fun areProspectModelsEqual(prospectModel: ProspectModel, prospectModelDto: ProspectModelWithIdDto): Boolean {
        return Objects.equals(prospectModel.name, prospectModelDto.name) &&
                Objects.equals(prospectModel.email, prospectModelDto.email) &&
                Objects.equals(prospectModel.phone, prospectModelDto.phone) &&
                Objects.equals(prospectModel.polo, Polos.valueOf(prospectModelDto.polo!!)) &&
                Objects.equals(prospectModel.course, prospectModelDto.course) &&
                Objects.equals(prospectModel.cupom, prospectModelDto.cupom) &&
                Objects.equals(prospectModel.emailMarketing, prospectModelDto.emailMarketing)
    }




}
