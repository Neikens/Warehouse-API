package com.warehouse.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@SpringBootApplication
@EnableJpaRepositories("com.warehouse.api.repositories")  // Make sure repositories are scanned
@EntityScan("com.warehouse.api.models")  // Ensure entity scanning

@OpenAPIDefinition(
    info = Info(title = "Warehouse API", version = "1.0", description = "API for managing warehouse inventory")
)

class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@Configuration
@EnableCaching
class CacheConfig