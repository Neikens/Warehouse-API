package com.warehouse.api.controllers

import com.warehouse.api.models.Product
import com.warehouse.api.services.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/api/v1/products") // URL pattern for the Product API
class ProductController(private val productService: ProductService) {

    // Get all products
    @GetMapping
    fun getAllProducts(): List<Product> {
        return productService.getAllProducts()
    }

    // Get product by ID
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Int): ResponseEntity<Product> {
        val product = productService.getProductById(id)
        return if (product != null) {
            ResponseEntity.ok(product)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Create a new product
    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        return productService.createProduct(product)
    }

    // Update an existing product
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Int, @RequestBody updatedProduct: Product): ResponseEntity<Product> {
        val updated = productService.updateProduct(id, updatedProduct)
        return if (updated != null) {
            ResponseEntity.ok(updated)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Delete a product
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Int): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    private val logger = LoggerFactory.getLogger(ProductController::class.java)

    fun getProducts(): List<Product> {
        logger.info("Fetching all products")
        return productService.getAllProducts()
    }

}

