package com.ms.itec.application.service.employee.impl

import com.ms.itec.application.dto.request.CurriculoDto
import com.ms.itec.application.dto.response.EmployeeModelDto
import com.ms.itec.application.service.employee.IEmployeeModelService
import com.ms.itec.domain.entity.employee.EmployeeModel
import com.ms.itec.infrastructure.persistence.employee.IEmployeePersistence
import com.ms.itec.presentation.excepetion.OperationNotComplete
import com.ms.itec.presentation.excepetion.RecordNotFound
import com.ms.itec.presentation.mapper.FromDto
import com.ms.itec.presentation.mapper.FromEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
data class EmployeeServiceImpl(private val curriculoFileServiceImpl: CurriculoFileServiceImpl, private val employeePersistence: IEmployeePersistence): IEmployeeModelService {
    override fun saveEmployeeModel(employeeDto: CurriculoDto): EmployeeModel {
        val identifier = curriculoFileServiceImpl.saveCurriculoFile(employeeDto.curriculoFile)
        val employeeModel = FromDto().toEntity(employeeDto, identifier)
        return runCatching { employeePersistence.save(employeeModel) }.getOrElse {
            throw OperationNotComplete("Error saving employee", it)
        }
    }

    override fun deleteEmployeeModelById(id: String) {
        runCatching { employeePersistence.deleteById(id) }.getOrElse {
            throw OperationNotComplete("Error deleting employee", it)
        }
    }

    override fun findEmployeeModelById(id: String): EmployeeModel {
        return  employeePersistence.findById(id).orElseThrow {
            throw RecordNotFound("Record not found, id: $id")
        }
    }

    override fun updateEmployeeModel(employee: EmployeeModel): EmployeeModel {
        return runCatching { employeePersistence.save(employee) }.getOrElse {
            throw OperationNotComplete("Error updating employee", it)
        }
    }

    override fun getAllEmployeeModels(): List<EmployeeModel> {
        return runCatching { employeePersistence.findAll() }.getOrElse {
            throw OperationNotComplete("Error getting all employees", it)
        }
    }

    override fun findAll(pageable: Pageable): Page<EmployeeModel> {
        return runCatching { employeePersistence.findAll(pageable) }.getOrElse {
            throw OperationNotComplete("Error getting all employees", it)
        }
    }

    override fun search(polo: String?, timeOfExperience: String?, setor: String?): List<EmployeeModelDto> {

        val employees = employeePersistence.search(polo, timeOfExperience, setor).map { FromEntity().toDto(it) }

        return employees

    }


}

