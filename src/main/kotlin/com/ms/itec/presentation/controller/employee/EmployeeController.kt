package com.ms.itec.presentation.controller.employee

import com.ms.itec.application.dto.request.CurriculoDto
import com.ms.itec.application.service.employee.ICurriculoFileService
import com.ms.itec.application.service.employee.impl.CurriculoFileServiceImpl
import com.ms.itec.application.service.employee.impl.EmployeeServiceImpl
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

const val MULTIPART_FORM_DATA = "multipart/form-data"
@Controller
@RequestMapping("v1/employee")
class CurriculoFileController(private val curriculoFileService: CurriculoFileServiceImpl, private val employeeService: EmployeeServiceImpl){

    @PostMapping("/upload")
    fun uploadCurriculo(curriculoDto: CurriculoDto): ResponseEntity<Any> {
        return try {
            employeeService.saveEmployeeModel(curriculoDto)

            ResponseEntity.ok().body("File uploaded successfully.")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error uploading file: ${e.message}")
        }
    }

    @GetMapping("employee")
    fun getAllEmployeeModels(): ResponseEntity<Any> {
        return try {
            val employees = employeeService.getAllEmployeeModels()
            ResponseEntity.ok().body(employees)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting all employees: ${e.message}")
        }
    }


    @PostMapping("/uploadCurriculo", consumes = [MULTIPART_FORM_DATA])
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<Any> {
        return try {
            val fileIdentification = curriculoFileService.saveCurriculoFile(file)
            val downloadUri = "/file/download/${fileIdentification}"

            ResponseEntity.ok().body("File uploaded successfully. Download URI: $downloadUri")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: ${e.message}")
        }
    }

    @GetMapping("/download/{fileId}")
    fun downloadFile(@PathVariable fileId: String): ResponseEntity<ByteArrayResource> {
        // Retrieve the file from the database using the fileId
        val fileData = curriculoFileService.getCurriculoFileById(fileId)

        // Set the response headers
        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${fileData.name}\"")

        // Create a ByteArrayResource from the file data
        val resource = ByteArrayResource(fileData.byte)

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .headers(headers)
            .body(resource)
    }
}
