package com.danilo.repository.impl

import com.danilo.model.Product
import com.danilo.repository.ProductRepository
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import java.util.UUID
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl(private val cqlSession: CqlSession) : ProductRepository {

    override fun saveCql(product: Product): Product {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "INSERT INTO product.Product(id,name,price,type,description) VALUES (?,?,?,?,?)",
                    UUID.randomUUID(),
                    product.name,
                    product.price,
                    product.type,
                    product.description
                )
        )
        return product
    }


    override fun getAllCql(product: Product): List<Product> {
        val selectResult = cqlSession.execute(
            (
                    SimpleStatement
                        .newInstance(
                            "SELECT * FROM product.Product",
                        )
                    )
        )
        return selectResult.map {
            Product(
                it.getUuid("id"),
                it.getString("name")!!,
                it.getBigDecimal("price")!!,
                it.getString("type")!!,
                it.getString("description")!!
            )
        }.toList()

    }


    override fun updateCql(id: UUID, product: Product): Product {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "UPDATE product.Product SET name = ?, price = ?, type = ?,description = ? WHERE id = ?",
                    product.name,
                    product.price,
                    product.type,
                    product.description,
                    id
                )
        )
        return product
    }

    override fun deleteCql(id: UUID) {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "DELETE FROM product.Product WHERE id = ?",
                    id
                )
        )
    }

    override fun getByIdCql(id: UUID): Product? {
        val selectResult = cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "SELECT * FROM product.Product WHERE id = ?",
                    id
                )
        )
        return selectResult
            .map { product ->
                Product(
                    product.getUuid("id")!!, product.getString("name")!!,
                    product.getBigDecimal("price")!!,
                    product.getString("description")!!,
                    product.getString("type")!!
                )
            }.firstOrNull()
    }
}




