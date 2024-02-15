package com.ms.itec.domain.repository.employee

import com.ms.itec.domain.entity.employee.EmployeeModel



interface IEmployeeModelRepository {

    fun getEmployeeBySetor(setor: String): List<EmployeeModel>



}