package com.example.myapplication.model.data

data class CardRequest(
    val orderId: String,
    val card: Card,
    val returnUrl: String,
    val sca:String
)

data class Card(
    val number: String,
    val expirationMonth: String,
    val expirationYear: String,
    val securityCode: String,
    val billingAddress: Address,
)

data class Address(
    val streetAddress: String,
    val extendedAddress: String,
    val locality: String,
    val region: String,
    val postalCode: String,
    val countryCode:String
)
