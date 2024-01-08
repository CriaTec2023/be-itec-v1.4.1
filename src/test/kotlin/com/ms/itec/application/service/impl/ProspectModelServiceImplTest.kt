package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.ProspectModelDto
import com.ms.itec.application.dto.request.ProspectModelWithIdDto
import com.ms.itec.domain.entity.ProspectModel
import com.ms.itec.infrastructure.persistence.IProspectModelPersistence
import com.ms.itec.presentation.mapper.FromDto
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.util.*

class ProspectModelServiceImplTest {

    private lateinit var prospectModelService: ProspectModelServiceImpl
    private lateinit var prospectPersistence: IProspectModelPersistence

    @BeforeEach
    fun setUp() {
        prospectPersistence = mock(IProspectModelPersistence::class.java)
        prospectModelService = ProspectModelServiceImpl(prospectPersistence)
    }


    @Test
    fun givenAValidParams_WhenCallProspectPersistenceSave_thenSave(){

        val prospectDto = ProspectModelDto(
                "Exemple",
                "test@gmail.com",
                "(24)9990-39239",
                "VR_VILA",
                "Marketing Digital",
                "",
            emailMarketing = true,
        )

        `when`(prospectPersistence.save(ArgumentMatchers.any())).thenReturn(FromDto().toEntity(prospectDto))


        val prospect: ProspectModel = prospectModelService.save(prospectDto)

        assertEquals(prospectDto.name,prospect.name)
        assertEquals(prospectDto.polo, prospect.polo.toString())
        assertEquals(prospectDto.course, prospect.course)
        assertEquals(prospect.cupom,"")
        assertTrue(prospectDto.emailMarketing == prospect.emailMarketing)

    }
    @Test
    fun givenAInvalidParams_WhenCallProspectPersistenceSave_thenThrowIllegalArgumentExecption() {

        val prospectDto = ProspectModelDto(
            "Exemple",
            "test@gmail.com",
            "(24)9990-39239",
            "VR VILA",
            "Marketing Digital",
            "",
            emailMarketing = true,
        )

        assertThrows(IllegalArgumentException::class.java){
            prospectModelService.save(prospectDto)
        }


    }
    @Test
    fun givenAValidParams_WhenCallProspectPersistenceGetWithOwner_thenGet() {

        val expectedOwnerId = UUID.randomUUID().toString()
        val expectedNumber = "(24) 999039239"

         val listModelDto = listOf(
             FromDto().toEntity(
                 ProspectModelDto(
                 "Exemple",
                 "test@gmail.com",
                 "(249990-39239",
                 "VR_VILA",
                 "Marketing Digital",
                 "",
                 emailMarketing = true,
                )
             ),
             FromDto().toEntity(
                 ProspectModelDto(
                     "Exemple 2",
                     "test2@gmail.com",
                     "24999039239",
                     "VR_RETIRO",
                     "Fisica",
                     "",
                     emailMarketing = true,
                 )
             ),FromDto().toEntity(
                 ProspectModelDto(
                     "Exemple 3",
                     "test3@gmail.com",
                     "(24)9990-39239",
                     "ANGRA_DOS_REIS",
                     "ENGENHARIA DE COMPUTAÇÃO",
                     "",
                     emailMarketing = false,
                 )
             )
         )

        listModelDto.forEach {
            user -> user.ownerId = expectedOwnerId
        }

        `when`(prospectPersistence.getWithIdOwner(expectedOwnerId)).thenReturn(Optional.of(listModelDto))

        prospectModelService.getWithOwner(expectedOwnerId).forEach {
            prospectModel -> assertEquals(expectedOwnerId, prospectModel.ownerId)
            assertEquals(expectedNumber, prospectModel.phone)
        }


    }



    @Test
    fun givenAValidParams_WhenCallProspectPersistenceGetWithoutOwner_thenGet() {
        val expectedOwnerId = ""
        val expectedNumber = "(24) 999039239"

        val listModelDto = listOf(
            FromDto().toEntity(
                ProspectModelDto(
                    "Exemple",
                    "test@gmail.com",
                    "(249990-39239",
                    "VR_VILA",
                    "Marketing Digital",
                    "",
                    emailMarketing = true,
                )
            ),
            FromDto().toEntity(
                ProspectModelDto(
                    "Exemple 2",
                    "test2@gmail.com",
                    "24999039239",
                    "VR_RETIRO",
                    "Fisica",
                    "",
                    emailMarketing = true,
                )
            ),FromDto().toEntity(
                ProspectModelDto(
                    "Exemple 3",
                    "test3@gmail.com",
                    "(24)9990-39239",
                    "ANGRA_DOS_REIS",
                    "ENGENHARIA DE COMPUTAÇÃO",
                    "",
                    emailMarketing = false,
                )
            )
        )

        `when`(prospectPersistence.getWithIdOwner(expectedOwnerId)).thenReturn(Optional.of(listModelDto))

        prospectModelService.getWithOwner(expectedOwnerId).forEach {
                prospectModel -> assertEquals(expectedOwnerId, prospectModel.ownerId)
            assertEquals(expectedNumber, prospectModel.phone)
        }
    }

    @Test
    fun givenEqualsParams_WhenCallProspectPersistenceUpdate_thenUpdate() {

        val expectedId = UUID.randomUUID().toString()


        val prospectModelDto = ProspectModelWithIdDto(
            id = expectedId,
            "Exemple",
            "test@gmail.com",
            "(249990-39239",
            "VR_VILA",
            "Marketing Digital",
            "",
            emailMarketing = true,
        )

        val prospectModel =  FromDto().toEntity(prospectModelDto)


        `when`(prospectPersistence.findById(prospectModel.id)).thenReturn(Optional.of(prospectModel))

        prospectModelService.update(prospectModelDto)

        verify( prospectPersistence,times(1)).save(prospectModel)

    }

    @Test
    fun givenValidParams_whenUpdate_thenProspectModelUpdated() {

        val expectedId = UUID.randomUUID().toString()
        val prospectModelDto = ProspectModelWithIdDto(
            id = expectedId,
            "Exemple",
            "test@gmail.com",
            "(249990-39239",
            "VR_VILA",
            "Marketing Digital",
            "",
            emailMarketing = true,
        )

        val prospectModel =  FromDto().toEntity(prospectModelDto)

        `when`(prospectPersistence.findById(expectedId)).thenReturn(Optional.of(prospectModel))
        `when`(prospectPersistence.save(any())).thenReturn(prospectModel)

        val updatedProspectModel = prospectModelService.update(prospectModelDto)

        verify(prospectPersistence).findById(expectedId)
        verify(prospectPersistence).save(any())
        assertEquals(prospectModelDto.name, updatedProspectModel.name)
        //... assert other fields
    }

    @Test
    fun givenAValidParams_whenDelete_ThenDelete() {
        val expectedId = UUID.randomUUID().toString()
        val prospectModelDto = ProspectModelWithIdDto(
            id = expectedId,
            "Exemple",
            "test@gmail.com",
            "(249990-39239",
            "VR_VILA",
            "Marketing Digital",
            "",
            emailMarketing = true,
        )

        val prospectModel =  FromDto().toEntity(prospectModelDto)

        `when`(prospectPersistence.findById(expectedId)).thenReturn(Optional.of(prospectModel))

        prospectModelService.delete(expectedId)

        verify(prospectPersistence, times(1)).delete(prospectModel)

    }

    @Test
    fun updateContacted() {
    }
}