package com.dm.berxley.stylishstore.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey val id: Int,
    val image: String,
    val name: String,
    val creationAt: String,
    val updatedAt: String
)