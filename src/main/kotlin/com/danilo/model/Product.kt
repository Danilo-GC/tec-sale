package com.danilo.model

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.util.UUID

@Introspected
data class Product(

    val id: UUID? = null,
    val name: String = "",
    val price: BigDecimal = BigDecimal.ZERO,
    val type: String? = "",
    val description: String? = "",
)