package com.example.myapplication.model.data

import kotlinx.serialization.Serializable

@Serializable
data class OrderID(
    val id: String,
    val status: String
)
