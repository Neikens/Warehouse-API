package com.warehouse.api.models

import jakarta.persistence.*

@Entity
@Table(name = "\"products\"")  // Ensure it matches your database table name
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    var description: String,

    @Column(nullable = false)
    var price: Double,

    @Column(nullable = false)
    var stock: Int
)
