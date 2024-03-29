package com.ms.itec.application.service.employee.impl

import com.ms.itec.application.dto.request.CurriculoDto
import com.ms.itec.application.dto.response.EmployeeModelDto
import com.ms.itec.application.service.employee.IEmployeeModelService
import com.ms.itec.domain.entity.employee.EmployeeModel
import com.ms.itec.infrastructure.persistence.employee.IEmployeePersistence
import com.ms.itec.presentation.excepetion.OperationNotCompletedException
import com.ms.itec.presentation.excepetion.RecordNotFoundException
import com.ms.itec.presentation.mapper.FromDto
import com.ms.itec.presentation.mapper.FromEntity
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
data class EmployeeServiceImpl(private val curriculoFileServiceImpl: CurriculoFileServiceImpl, private val employeePersistence: IEmployeePersistence): IEmployeeModelService {
    @Transactional
    override fun saveEmployeeModel(employeeDto: CurriculoDto): EmployeeModel {
        val identifier = curriculoFileServiceImpl.saveCurriculoFile(employeeDto.curriculoFile)
        val employeeModel = FromDto().toEntity(employeeDto, identifier)
        return runCatching { employeePersistence.save(employeeModel) }.getOrElse {
            throw OperationNotCompletedException("Error saving employee", it)
        }
    }
    @Transactional
    override fun deleteEmployeeModelById(id: String) {
        runCatching { employeePersistence.deleteById(id) }.getOrElse {
            throw OperationNotCompletedException("Error deleting employee", it)
        }
    }

    override fun findEmployeeModelById(id: String): EmployeeModel {
        return  employeePersistence.findById(id).orElseThrow {
            throw RecordNotFoundException("Record not found, id: $id")
        }
    }
    @Transactional
    override fun updateEmployeeModel(employee: EmployeeModel): EmployeeModel {
        return runCatching { employeePersistence.save(employee) }.getOrElse {
            throw OperationNotCompletedException("Error updating employee", it)
        }
    }

    override fun getAllEmployeeModels(): List<EmployeeModel> {
        return runCatching { employeePersistence.findAll() }.getOrElse {
            throw OperationNotCompletedException("Error getting all employees", it)
        }
    }

    override fun findAll(pageable: Pageable): Page<EmployeeModel> {
        // para realizar a consulta paginada no banco de dados
        return employeePersistence.findAll(pageable)

    }

    override fun search(polo: String?, timeOfExperience: String?, setor: String?, education: String?): List<EmployeeModelDto> {

        val employees = employeePersistence.search(polo, timeOfExperience, setor,education).map { FromEntity().toDto(it) }

        return employees

    }


}

