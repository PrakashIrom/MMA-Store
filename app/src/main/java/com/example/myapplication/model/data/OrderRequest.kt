package com.example.myapplication.model.data

import kotlinx.serialization.Serializable


@Serializable
data class PayPalOrderRequest(
    val intent: String ,
    val purchase_units: List<PurchaseUnit>
)

@Serializable
data class PurchaseUnit(
    val amount: Amount
)

@Serializable
data class Amount(
    val currency_code: String ,
    val value: String
)
