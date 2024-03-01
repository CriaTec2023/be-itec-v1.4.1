package com.ms.itec.infrastructure.persistence.employee

import com.ms.itec.domain.entity.employee.EmployeeModel
import com.ms.itec.domain.repository.employee.IEmployeeModelRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface IEmployeePersistence: JpaRepository<EmployeeModel, String>, IEmployeeModelRepository {

    override fun getEmployeeBySetor(setor: String): List<EmployeeModel>
    @Query("SELECT e FROM EmployeeModel e WHERE (:polo IS NULL OR e.polo = :polo) AND (:timeOfExperience IS NULL OR e.timeOfExperience = :timeOfExperience) AND (:setor IS NULL OR e.setor = :setor)")
    override fun search(
            @Param("polo") polo: String?,
            @Param("timeOfExperience") timeOfExperience: String?,
            @Param("setor") setor: String?
    ): List<EmployeeModel>
}