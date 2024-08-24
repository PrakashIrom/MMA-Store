package com.example.myapplication.model.data

import kotlinx.serialization.Serializable

@Serializable
data class Apparel(
        val name: String,
        val imgUri: String,
        val price: String,
        val gender: String,
        val quantity: String
    )
