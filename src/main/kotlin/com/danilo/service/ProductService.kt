package com.danilo.service

import com.danilo.model.Product
import java.util.UUID

interface ProductService {

    fun getById(id: UUID): Product?
    fun getAll(): List<Product>
    fun addProduct(product: Product): Product
    fun updateProduct(id: UUID, product: Product): Product
    fun deleteProduct(id: UUID)
}