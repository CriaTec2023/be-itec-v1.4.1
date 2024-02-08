package com.ms.itec.domain.repository.employee

import com.ms.itec.domain.entity.employee.EmployeeModel

interface IEmployeeModelRepository {

    fun saveEmployeeModel(employee: EmployeeModel): EmployeeModel

    fun deleteEmployeeModelById(id: String)

    fun findEmployeeModelById(id: String): EmployeeModel

    fun updateEmployeeModel(employee: EmployeeModel): EmployeeModel
}