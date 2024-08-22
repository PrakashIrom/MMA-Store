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

//mutableMapOf<Apparel,Size>()
//if the selected item is in the map then display text=map[apparel]