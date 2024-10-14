package com.dm.berxley.stylishstore.domain.models.payloads

import com.dm.berxley.stylishstore.Constants

data class RegisterPayload(
    val name: String,
    val email: String,
    val password: String,
    val avatar: String = Constants.DEFAULT_AVATAR_URL
)
