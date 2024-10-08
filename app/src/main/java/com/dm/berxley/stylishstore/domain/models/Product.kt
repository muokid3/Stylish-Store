package com.dm.berxley.stylishstore.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val images: List<String>,
    val category: Category,
    val creationAt: String,
    val updatedAt: String
)