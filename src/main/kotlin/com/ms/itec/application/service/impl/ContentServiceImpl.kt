package com.ms.itec.application.service.impl

import com.ms.itec.application.dto.request.ContentDto
import com.ms.itec.application.dto.request.ContentDtoWithId
import com.ms.itec.application.enums.Tag
import com.ms.itec.application.service.IContentService
import com.ms.itec.domain.entity.content.Content
import com.ms.itec.infrastructure.persistence.IContentPersistence
import com.ms.itec.presentation.excepetion.OperationNotComplete
import com.ms.itec.presentation.excepetion.RecordNotFound
import com.ms.itec.presentation.mapper.FromDto
import jakarta.validation.Valid
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContentServiceImpl(private val contentPersistence: IContentPersistence): IContentService {


    override fun save(contentDto: ContentDto): Content {
        val content: Content = FromDto().toEntity(contentDto)
        return runCatching { contentPersistence.save(content) }.getOrElse {
            throw OperationNotComplete("Error saving content", it)
        }
    }

    override fun update(@Valid contentDto: ContentDtoWithId): Content {
        val contentRecord = contentPersistence.findById(contentDto.id).orElseThrow {
            throw RecordNotFound("Record not found, id: ${contentDto.id}")
        }

        return if (!areContentsEqual(contentRecord, contentDto)) {

            contentRecord.apply {
                title = contentDto.title
                description = contentDto.description
                content = contentDto.content
                background = contentDto.background
                tag = Tag.valueOf(contentDto.tag)
                avgSalary = contentDto.avgSalary
            }
            contentPersistence.save(contentRecord)
        } else {
            contentRecord
        }

    }

    override fun getAll(): List<Content> {
        val result = runCatching { contentPersistence.findAll() }
        return result.getOrElse { emptyList() }
    }

    override fun delete(id: String) {
        runCatching { contentPersistence.deleteById(id) }.getOrElse {
            throw RecordNotFound("Record not found, id: $id")
        }
    }

    override fun retriveByTag(tagParam:String):List<Content>{

        val tag = try {
            Tag.valueOf(tagParam)
        } catch (ex: IllegalArgumentException) {
            throw IllegalArgumentException("Valor de tag inválido: $tagParam", ex)
        }

        return if (tag != Tag.UNDEFINED) {
            runCatching { contentPersistence.retriveByTag(tag) }.getOrElse { emptyList() }
        }else{
            emptyList()
        }

    }
    private fun areContentsEqual(contentRecord: Content, contentDto: ContentDtoWithId): Boolean {
        return Objects.equals(contentRecord.title, contentDto.title) &&
                Objects.equals(contentRecord.description, contentDto.description) &&
                Objects.equals(contentRecord.content, contentDto.content) &&
                Objects.equals(contentRecord.background, contentDto.background) &&
                Objects.equals(contentRecord.tag, Tag.valueOf(contentDto.tag)) &&
                Objects.equals(contentRecord.avgSalary, contentDto.avgSalary)
    }


}