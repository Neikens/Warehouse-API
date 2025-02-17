package com.warehouse.api.services

import com.warehouse.api.models.Product
import com.warehouse.api.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.cache.annotation.Cacheable


@Service
class ProductService(@Autowired private val productRepository: ProductRepository) {

    @Cacheable("products")
    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun getProductById(id: Int): Product? {
        return productRepository.findById(id).orElse(null)
    }

    fun createProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun updateProduct(id: Int, updatedProduct: Product): Product? {
        return if (productRepository.existsById(id)) {
            val existingProduct = productRepository.findById(id).orElse(null) ?: return null

            // Retain the same ID and update other fields
            val updatedEntity = existingProduct.copy(
                name = updatedProduct.name,
                description = updatedProduct.description,
                price = updatedProduct.price,
                stock = updatedProduct.stock
            )

            productRepository.save(updatedEntity)
        } else {
            null
        }
    }

    fun deleteProduct(id: Int) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
        }
    }

}
