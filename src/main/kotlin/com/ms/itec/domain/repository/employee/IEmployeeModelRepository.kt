package com.ms.itec.domain.repository.employee

import com.ms.itec.domain.entity.employee.EmployeeModel


interface IEmployeeModelRepository {

    fun getEmployeeBySetor(setor: String): List<EmployeeModel>


    fun search(polo: String?, timeOfExperience: String?, setor: String?, education: String?): List<EmployeeModel>


}