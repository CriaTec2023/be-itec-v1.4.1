package com.ms.itec.infrastructure.persistence.employee

import com.ms.itec.domain.entity.employee.EmployeeModel
import com.ms.itec.domain.repository.employee.IEmployeeModelRepository
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Transactional
interface IEmployeePersistence: JpaRepository<EmployeeModel, String>, IEmployeeModelRepository {

    override fun getEmployeeBySetor(setor: String): List<EmployeeModel>


}