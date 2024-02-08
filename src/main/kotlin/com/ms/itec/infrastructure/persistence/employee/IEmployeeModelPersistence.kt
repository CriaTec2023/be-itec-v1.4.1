package com.ms.itec.infrastructure.persistence.employee

import com.ms.itec.domain.entity.employee.EmployeeModel
import com.ms.itec.domain.repository.employee.IEmployeeModelRepository
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
@Transactional
interface IEmployeeModelPersistence: JpaRepository<EmployeeModel, String> , IEmployeeModelRepository {
    override fun saveEmployeeModel(employee: EmployeeModel): EmployeeModel {
        return save(employee)
    }

    override fun deleteEmployeeModelById(id: String) {
        deleteById(id)
    }

    override fun findEmployeeModelById(id: String): EmployeeModel {
        return findById(id).get()
    }

    override fun updateEmployeeModel(employee: EmployeeModel): EmployeeModel {
        return save(employee)
    }


}