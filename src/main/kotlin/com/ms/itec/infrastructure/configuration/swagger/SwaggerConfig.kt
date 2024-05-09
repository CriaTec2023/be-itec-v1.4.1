package com.ms.itec.infrastructure.configuration.swagger
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("API RESTFUL ITEC")
                    .description("api for the itec website with candidate bank, saving interested leads, ombudsman and content management.")
                    .contact(
                        Contact()
                            .name("Luis Felipe Mota")
                            .email("lupesms97@gmail.com")
                    )
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("https://spingdoc.org.com")
                    )
            )
    }
}