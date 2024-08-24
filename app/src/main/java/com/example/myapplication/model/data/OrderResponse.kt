package com.example.myapplication.model.data

import kotlinx.serialization.Serializable

@Serializable
data class OrderResponse(
    val id: String,
    val status: String,
    val links: List<Link>
)

@Serializable
data class Link(
    val href: String,
    val rel: String,
    val method: String
)