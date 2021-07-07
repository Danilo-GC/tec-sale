package com.danilo.model

import java.math.BigDecimal
import java.util.UUID

data class Product(

    val id: UUID? = null,
    val name: String = "",
    val price: BigDecimal = BigDecimal.ZERO,
    val type: String? = "",
    val description: String? = "",
)