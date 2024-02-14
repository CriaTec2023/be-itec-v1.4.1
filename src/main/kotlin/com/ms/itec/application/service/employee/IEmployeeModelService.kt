package com.ms.itec.application.service.employee

import com.ms.itec.application.dto.request.CurriculoDto
import com.ms.itec.domain.entity.employee.EmployeeModel

interface IEmployeeModelService {

    fun saveEmployeeModel(employeeDto: CurriculoDto): EmployeeModel

    fun deleteEmployeeModelById(id: String)

    fun findEmployeeModelById(id: String): EmployeeModel

    fun updateEmployeeModel(employee: EmployeeModel): EmployeeModel

    fun getAllEmployeeModels(): List<EmployeeModel>


}