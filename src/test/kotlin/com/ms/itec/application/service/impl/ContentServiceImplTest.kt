package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.ContentDto
import com.ms.itec.application.dto.request.ContentDtoWithId
import com.ms.itec.application.enums.Tag
import com.ms.itec.entity.Content
import com.ms.itec.infrastructure.persistence.ContentPersistence
import com.ms.itec.presentation.excepetion.OperationNotComplete
import com.ms.itec.presentation.excepetion.RecordNotFound
import com.ms.itec.presentation.mapper.FromDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import java.util.*

class ContentServiceImplTest {


    private lateinit var contentPersistence: ContentPersistence
    private lateinit var contentServiceImpl: ContentServiceImpl

    @BeforeEach
    fun setUp() {
        contentPersistence = mock(ContentPersistence::class.java)
        contentServiceImpl = ContentServiceImpl(contentPersistence)
    }

    @Test
    fun givenAInvalidParams_whenCallContentSave_thenThrowAhandleValidationExceptions() {
        val contentDto = ContentDto(
            tag = "BACKEND",
            title = "Java",
            description = "Java",
            content = "Java",
            background = "Java",
            avgSalary = 0.0
        )

        assertThrows(IllegalArgumentException::class.java) {
            contentServiceImpl.save(contentDto)
        }
    }
    @Test
    fun givenvalidParams_whenCallContentSave_thenSaveOnPersistence() {
        val contentDto = ContentDto(
            tag = "POST",
            title = "Java",
            description = "Java",
            content = "Java",
            background = "Java",
            avgSalary = 0.0
        )


        `when`(contentPersistence.save(any())).thenReturn(
            FromDto().toEntity(contentDto)
        )

        val content: Content = contentServiceImpl.save(contentDto)

        assert(content.id.isNotEmpty())
        assertEquals(contentDto.title, content.title)
        assertEquals(contentDto.description, content.description)
        assertEquals(contentDto.content, content.content)
        assertEquals(contentDto.background, content.background)
        assertEquals(Tag.valueOf(contentDto.tag), content.tag)
        assertEquals(contentDto.avgSalary, content.avgSalary)


    }
    @Test
    fun whenCallAMethodSave_thenThrowOperationNotCompletedException() {
        val contentDto = ContentDto(
            tag = "POST",
            title = "Java",
            description = "Java",
            content = "Java",
            background = "Java",
            avgSalary = 0.0
        )


        `when`(contentPersistence.save(any())).
        thenThrow(OperationNotComplete("Error saving content"))


        assertThrows(OperationNotComplete::class.java) {
            contentServiceImpl.save(contentDto)
        }

    }

    @Test
    fun givenAvalidParams_whenCallContentUpdate_thenSaveNewsOneOn() {

        val contentDto = ContentDtoWithId(
            id = UUID.randomUUID().toString(),
            tag = "POST",
            title = "Post title ",
            description = "Post description",
            content = "Post content",
            background = "Post background",
            avgSalary = 0.0
        )
        
        val content = Content(
           id = contentDto.id,
           tag = Tag.valueOf("BLOG"),
            content = "Content on Record",
            description = "Description on Record",
            title = "Title on Record",
            background = "Background on Record",
            avgSalary = 0.2
        ) 

        `when`(contentPersistence.findById(any())).thenReturn(
            Optional.of(content)
        )
        `when`(contentPersistence.save(any())).thenReturn(
            content
        )

        contentServiceImpl.update(contentDto)

        assertEquals(contentDto.id, content.id)
        assertEquals(contentDto.title, content.title)
        assertEquals(contentDto.description, content.description)
        assertEquals(contentDto.content, content.content)
        assertEquals(contentDto.background, content.background)
        assertEquals(Tag.valueOf(contentDto.tag), content.tag)
        assertEquals(contentDto.avgSalary, content.avgSalary)

        
    }

    @Test
    fun givenAInvalidId_whenCallContentUpdate_thenThrowRecordNotFoundException() {

        val contentDto = ContentDtoWithId(
            id = UUID.randomUUID().toString(),
            tag = "POST",
            title = "Post title ",
            description = "Post description",
            content = "Post content",
            background = "Post background",
            avgSalary = 0.0
        )

        `when`(contentPersistence.findById(any())).thenThrow(
            RecordNotFound("Record not found, id: ${contentDto.id}")
        )

        assertThrows(RecordNotFound::class.java) {
            contentServiceImpl.update(contentDto)
        }


    }

    @Test
    fun givenTheSameFields_whenCallContentUpdate_thenNotSaving() {

        val contentDto = ContentDtoWithId(
            id = UUID.randomUUID().toString(),
            tag = "POST",
            title = "Post title ",
            description = "Post description",
            content = "Post content",
            background = "Post background",
            avgSalary = 0.0
        )

        val content = Content(
            id = contentDto.id,
            tag = Tag.valueOf(contentDto.tag),
            content = contentDto.content,
            description = contentDto.description,
            title = contentDto.title,
            background = contentDto.background,
            avgSalary = contentDto.avgSalary

        )


        `when`(contentPersistence.findById(any())).thenReturn(
            Optional.of(content)
        )

        contentServiceImpl.update(contentDto)

        verify(contentPersistence, times(0)).save(any())



    }
    @Test
    fun whenCallGetAll_thenReturnAListOfValidContent() {
        val expectId = UUID.randomUUID().toString()
        val expectId2 = UUID.randomUUID().toString()


        `when`(contentPersistence.findAll())
            .thenReturn(
            listOf(
                Content(
                    id = expectId,
                    tag = Tag.POST,
                    title = "Post title ",
                    description = "Post description",
                    content = "Post content",
                    background = "Post background",
                    avgSalary = 0.0
                ),
                Content(
                    id = expectId2,
                    tag = Tag.BLOG,
                    title = "Post title ",
                    description = "Post description",
                    content = "Post content",
                    background = "Post background",
                    avgSalary = 0.0
                )
            )
        )

        val contents = contentServiceImpl.getAll()

        assertEquals(2, contents.size)
        assertEquals(expectId, contents[0].id)
        assertEquals(expectId2, contents[1].id)


    }

    @Test
    fun givenAEmptyList_whenCallGetAll_thenReturnAEmptyListOfContent() {

        `when`(contentPersistence.findAll()).thenReturn(emptyList())

        val contents = contentServiceImpl.getAll()

        assertTrue(contents.isEmpty())

    }

}