package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.domain.entity.ProspectModel
import com.ms.itec.infrastructure.persistence.IProspectModelPersistence
import com.ms.itec.presentation.mapper.FromDto
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

class ProspectModelServiceImplTest {

    private lateinit var prospectModelService: ProspectModelServiceImpl
    private lateinit var prospectPersistence: IProspectModelPersistence

    @BeforeEach
    fun setUp() {
        prospectPersistence = Mockito.mock(IProspectModelPersistence::class.java)
        prospectModelService = ProspectModelServiceImpl(prospectPersistence)
    }


//    TESTAR COMO É PROCESSADA A INFORMAÇÃO QUE CHEGAR NO MAPPER E COMO ELA SAIA

    @Test
    fun givenAValidParams_WhenCallProspectPersistenceSave_thenSave(){

        val prospectDto = ProspectModelDto(
                "Exemple",
                "test@gmail.com",
                "(24)9990-39239",
                "VR_VILA",
                "Marketing Digital",
                "",
                true,
        )

        Mockito.`when`(prospectPersistence.save(ArgumentMatchers.any())).thenReturn(
                FromDto().toEntity(prospectDto))


        val prospect: ProspectModel = prospectModelService.save(prospectDto)

        assertEquals(prospectDto.name,prospect.name)
        assertEquals(prospectDto.polo, prospect.polo.toString())
        assertEquals(prospectDto.course, prospect.course)
        assertEquals(prospect.cupom,"")
        assertTrue(prospectDto.emailMarketing.equals(prospect.emailMarketing))



    }

    @Test
    fun getWithOwner() {
    }

    @Test
    fun getWithoutOwner() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun updateContacted() {
    }
}