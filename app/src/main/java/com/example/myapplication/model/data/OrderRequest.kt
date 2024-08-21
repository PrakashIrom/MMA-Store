package com.example.myapplication.model.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PayPalOrderRequest(
    @SerializedName("intent")
    val intent: String = "CAPTURE",  // CAPTURE or AUTHORIZE

    @SerializedName("purchase_units")
    val purchaseUnits: List<PurchaseUnit>
)

@Serializable
data class PurchaseUnit(
    @SerializedName("amount")
    val amount: Amount
)

@Serializable
data class Amount(
    @SerializedName("currency_code")
    val currencyCode: String,  // e.g., "USD"

    @SerializedName("value")
    val value: String  // e.g., "5.00"
)