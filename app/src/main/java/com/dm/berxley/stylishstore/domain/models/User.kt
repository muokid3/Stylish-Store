package com.dm.berxley.stylishstore.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    val avatar: String,
    val creationAt: String,
    val email: String,
    val name: String,
    val password: String,
    val role: String,
    val updatedAt: String
)