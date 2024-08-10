package com.example.myapplication.model.data

import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenResponse(
    val scope: String,
    val access_token: String,
    val token_type: String,
    val app_id: String,
    val expires_in: Int,
    val nonce: String
)
