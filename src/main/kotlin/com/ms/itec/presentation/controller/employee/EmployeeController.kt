package com.ms.itec.presentation.controller.employee

import com.ms.itec.application.dto.request.CurriculoDto
import com.ms.itec.application.dto.response.DtoResponse
import com.ms.itec.application.service.employee.impl.CurriculoFileServiceImpl
import com.ms.itec.application.service.employee.impl.EmployeeServiceImpl
import com.ms.itec.presentation.mapper.FromEntity
import org.springframework.core.io.ByteArrayResource
import org.springframework.data.domain.Pageable
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
    fun uploadCurriculo(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("name") name: String,
        @RequestParam("email") email: String,
        @RequestParam("polo") polo: String,
        @RequestParam("phone") phone: String,
        @RequestParam("setor") setor: String,
        @RequestParam("lgpd") lgpd: Boolean,
        @RequestParam("timeOfExperience") timeOfExperience: String
    ): ResponseEntity<Any> {
        return try {
            val curriculoDto = CurriculoDto(
                name = name,
                email = email,
                polo = polo,
                phone = phone,
                setor = setor,
                curriculoFile = file,
                lgpd = lgpd,
                timeOfExperience = timeOfExperience
            )
            employeeService.saveEmployeeModel(curriculoDto)
            val response = DtoResponse(
                status = 200,
                sucess = true,
                error = "",
            )

            ResponseEntity.ok().body(response)
        } catch (e: Exception) {
            val response = DtoResponse(
                status = 400,
                sucess = false,
                error = e.message!!.toString(),
            )
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
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

    @GetMapping("/employee")
    fun getAllEmployeeModels(): ResponseEntity<Any> {
        return try {
            val employees = employeeService.getAllEmployeeModels().map { FromEntity().toDto(it) }
            ResponseEntity.ok().body(employees)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting all employees: ${e.message}")
        }
    }


    @GetMapping("/employee/page")
    fun getEmployeePage(pegeable: Pageable): ResponseEntity<Any> {
        return try {
            val employees = employeeService.findAll(pegeable).map {FromEntity().toDto(it)}
            ResponseEntity.ok().body(employees)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting all employees: ${e.message}")
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
